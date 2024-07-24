package io.github.jonaskahn.extensions

import io.jooby.Extension
import io.jooby.Jooby
import jakarta.validation.Validation
import jakarta.validation.Validator

class ValidatorModule : Extension {
    companion object {
        private val validator = Validation.buildDefaultValidatorFactory().validator
    }

    override fun install(application: Jooby) {
        val registry = application.services
        registry.put(Validator::class.java, validator)
    }
}