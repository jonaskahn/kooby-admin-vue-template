package io.github.jonaskahn.services.user

import com.google.inject.ImplementedBy
import io.github.jonaskahn.controller.auth.UserRegisterRequest

@ImplementedBy(UserServiceImpl::class)
interface UserService {
    fun createUser(request: UserRegisterRequest)

    fun getCurrentUserInfo(): UserInfoDto

    fun getCurrentUserInfoWithExecutor(): UserInfoDto
}