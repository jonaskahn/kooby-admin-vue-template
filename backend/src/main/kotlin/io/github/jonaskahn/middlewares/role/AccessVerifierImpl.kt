package io.github.jonaskahn.middlewares.role

import io.github.jonaskahn.exception.ForbiddenAccessException
import io.github.jonaskahn.middlewares.context.UserContextHolder

internal class AccessVerifierImpl : AccessVerifier {

    override fun hasAnyRoles(vararg roles: String): Boolean {
        return UserContextHolder.getCurrentUserRoles()?.any { roles.contains(it) } ?: false
    }

    override fun requireAnyRoles(vararg roles: String) {
        if (!hasAnyRoles(*roles)) {
            throw ForbiddenAccessException()
        }
    }
}