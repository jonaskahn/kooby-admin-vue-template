package io.github.jonaskahn.controllers.user

import io.github.jonaskahn.services.user.UserDto
import io.github.jonaskahn.services.user.UserService
import io.jooby.annotation.GET
import io.jooby.annotation.POST
import io.jooby.annotation.Path
import jakarta.inject.Inject

@Path
class UserController @Inject constructor(private val userService: UserService) {

    @GET("/secure/user/info")
    fun info(): UserDto {
        return userService.getCurrentUserInfo()
    }

    @GET("/secure/user/info-with-executor")
    fun getInfo(): UserDto {
        return userService.getCurrentUserInfoWithExecutor()
    }

    @POST("/user/register")
    fun register(request: UserRegisterRequest) {
        userService.createUser(request)
    }
}