package io.github.jonaskahn.controllers.auth

import io.github.jonaskahn.assistant.Response
import io.github.jonaskahn.services.authen.AuthenticationService
import io.jooby.annotation.DELETE
import io.jooby.annotation.POST
import io.jooby.annotation.Path
import jakarta.inject.Inject
import jakarta.validation.Valid

@Path
class AuthController @Inject constructor(private val authenticationService: AuthenticationService) {

    @POST("/auth/generate-token")
    fun generateToken(@Valid request: GenerateTokenRequest): Response<String> {
        val token = authenticationService.generateToken(request.username!!, request.password!!, request.rememberMe)
        return Response.ok(token, "app.common.message.welcome")
    }

    @DELETE("/secure/auth/logout")
    fun logout() {
        authenticationService.logout()
    }
}