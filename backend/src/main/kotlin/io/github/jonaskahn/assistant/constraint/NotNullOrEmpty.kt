package io.github.jonaskahn.assistant.constraint

import jakarta.validation.Constraint
import kotlin.reflect.KClass


@MustBeDocumented
@Constraint(validatedBy = [NotNullOrEmptyConstrainValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class NotNullOrEmpty(
    val message: String = "Required field must be not null or empty",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Any>> = []
)