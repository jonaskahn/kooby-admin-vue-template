package io.github.jonaskahn.services.user

import com.google.inject.ImplementedBy
import io.github.jonaskahn.assistant.PageData
import io.github.jonaskahn.controllers.user.UserRegisterRequest
import io.github.jonaskahn.entities.enums.Status

@ImplementedBy(UserServiceImpl::class)
interface UserService {

    fun search(
        keyword: String? = null,
        statuses: Collection<Status> = listOf(Status.ACTIVATED),
        pageNo: Long = 0L
    ): PageData<UserDto>

    fun createUser(request: UserRegisterRequest)

    fun getCurrentUserInfo(): UserDto

    fun getCurrentUserInfoWithExecutor(): UserDto
}