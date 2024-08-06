package io.github.jonaskahn.controllers.auth

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.github.jonaskahn.assistant.constraint.NotNullOrEmpty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class GenerateTokenRequest(
    @JsonAlias("email")
    @field:NotNullOrEmpty("app.auth.validation.username")
    val username: String? = null,
    @field:NotNullOrEmpty("app.auth.validation.password")
    val password: String? = null,
    val rememberMe: Boolean = false,
)