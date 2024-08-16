package io.github.jonaskahn.middlewares.validate

import io.github.jonaskahn.assistant.Language
import io.github.jonaskahn.exception.ValidationException
import io.github.jonaskahn.middlewares.context.LanguageContextHolder
import jakarta.validation.Validation

object BeanValidator {

    fun validate(obj: Any?) {
        Validation.buildDefaultValidatorFactory().use { factory ->
            val result = factory.validator.validate(obj)
            if (result.isEmpty()) return
            val acceptLanguage: String = LanguageContextHolder.getLanguage()
            val data = result.reversed().mapNotNull { Language.of(acceptLanguage, it.message, arrayOf<String>()) }
            throw ValidationException(data)
        }
    }
}