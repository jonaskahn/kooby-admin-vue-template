package io.github.jonaskahn.middlewares.role

import io.github.jonaskahn.exception.ForbiddenAccessException
import io.jooby.Context
import jakarta.inject.Inject
import org.pac4j.core.profile.UserProfile

internal class AccessVerifierImpl @Inject constructor(private val context: Context) : AccessVerifier {

    override fun hasAnyRoles(vararg roles: String): Boolean {
        val user = context.getUser<UserProfile>()
        return user?.roles?.any { roles.contains(it) } ?: false
    }

    override fun requireAnyRoles(vararg roles: String) {
        if (!hasAnyRoles(*roles)) {
            throw ForbiddenAccessException()
        }
    }
}