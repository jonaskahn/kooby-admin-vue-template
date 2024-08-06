package io.github.jonaskahn.controllers.user

import com.fasterxml.jackson.annotation.JsonAlias

class UserRegisterRequest {
    @field:JsonAlias("full_name", "fullName")
    val name: String? = null
    val email: String? = null
    val username: String? = null
    var password: String? = null
}