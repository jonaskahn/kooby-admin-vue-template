package io.github.jonaskahn.assistant.constraint

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.apache.commons.lang3.ObjectUtils

internal class NotNullOrEmptyConstrainValidator : ConstraintValidator<NotNullOrEmpty, Any> {
    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        return ObjectUtils.isNotEmpty(value)
    }
}