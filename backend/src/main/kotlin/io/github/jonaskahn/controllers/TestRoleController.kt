package io.github.jonaskahn.controllers

import io.github.jonaskahn.constants.Roles
import io.github.jonaskahn.middlewares.role.AccessVerifier
import io.jooby.annotation.GET
import io.jooby.annotation.Path

@Path("/secure/test")
class TestRoleController {

    @GET("/admin-role")
    fun testAdmin(): String {
        AccessVerifier.requireRole(Roles.ADMIN)
        return "ok"
    }
}