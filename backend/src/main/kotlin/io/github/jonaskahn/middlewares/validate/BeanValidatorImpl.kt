package io.github.jonaskahn.middlewares.validate

import io.github.jonaskahn.assistant.Language
import io.github.jonaskahn.exception.ValidationException
import io.jooby.Context
import jakarta.inject.Inject
import jakarta.validation.Validator

internal class BeanValidatorImpl @Inject constructor(
    private val context: Context,
    private val validator: Validator
) : BeanValidator {

    override fun validate(obj: Any?) {
        val result = validator.validate(obj)
        if (result.isEmpty()) return
        val acceptLanguage: String? = context.header("Accept-Language").valueOrNull()
        val data = result.reversed().mapNotNull { Language.of(acceptLanguage, it.message, arrayOf<String>()) }
        throw ValidationException(data)
    }
}