package io.github.jonaskahn.services.user

import com.google.inject.ImplementedBy
import io.github.jonaskahn.controllers.user.UserRegisterRequest

@ImplementedBy(UserServiceImpl::class)
interface UserService {
    fun createUser(request: UserRegisterRequest)

    fun getCurrentUserInfo(): UserDto

    fun getCurrentUserInfoWithExecutor(): UserDto
}