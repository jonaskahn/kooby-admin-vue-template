package io.github.jonaskahn.middlewares.role

import com.google.inject.ImplementedBy

@ImplementedBy(AccessVerifierImpl::class)
interface AccessVerifier {

    fun hasRole(role: String): Boolean = hasAnyRoles(role)

    fun requireRole(role: String) = requireAnyRoles(role)

    fun hasAnyRoles(vararg roles: String): Boolean

    fun requireAnyRoles(vararg roles: String)
}