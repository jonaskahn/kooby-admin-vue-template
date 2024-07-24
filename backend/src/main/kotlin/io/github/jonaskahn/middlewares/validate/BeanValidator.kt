package io.github.jonaskahn.middlewares.validate

import com.google.inject.ImplementedBy

@ImplementedBy(BeanValidatorImpl::class)
interface BeanValidator {
    fun validate(obj: Any?)
}