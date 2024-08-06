package io.github.jonaskahn.controllers.auth

import io.github.jonaskahn.middlewares.validate.BeanValidator
import io.github.jonaskahn.services.authen.AuthenticationService
import io.jooby.annotation.DELETE
import io.jooby.annotation.POST
import io.jooby.annotation.Path
import jakarta.inject.Inject

@Path
class AuthController @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val beanValidator: BeanValidator
) {

    @POST("/auth/generate-token")
    fun generateToken(request: GenerateTokenRequest): String {
        beanValidator.validate(request)
        return authenticationService.generateToken(request.username!!, request.password!!, request.rememberMe)
    }

    @DELETE("/secure/auth/logout")
    fun logout() {
        authenticationService.logout()
    }
}