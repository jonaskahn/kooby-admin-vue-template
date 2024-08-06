package io.github.jonaskahn.controllers

import io.github.jonaskahn.constants.Roles
import io.github.jonaskahn.middlewares.role.AccessVerifier
import io.jooby.annotation.GET
import io.jooby.annotation.Path
import jakarta.inject.Inject

@Path("/secure/test")
class TestRoleController @Inject constructor(private val accessVerifier: AccessVerifier) {

    @GET("/admin-role")
    fun testAdmin(): String {
        accessVerifier.requireRole(Roles.ADMIN)
        return "ok"
    }
}