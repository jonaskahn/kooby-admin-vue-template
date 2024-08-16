package io.github.jonaskahn.middlewares.role

import io.github.jonaskahn.exception.ForbiddenAccessException
import io.github.jonaskahn.middlewares.context.UserContextHolder

object AccessVerifier {

    fun hasRole(role: String): Boolean = hasAnyRoles(role)

    fun requireRole(role: String) = requireAnyRoles(role)

    fun hasAnyRoles(vararg roles: String) = UserContextHolder.getCurrentUserRoles()?.any { roles.contains(it) } ?: false

    fun requireAnyRoles(vararg roles: String): () -> Unit = {
        if (!hasAnyRoles(*roles)) {
            throw ForbiddenAccessException()
        }
    }
}