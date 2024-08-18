package io.github.jonaskahn.services.user

import io.github.jonaskahn.assistant.PageData
import io.github.jonaskahn.constants.Jwt
import io.github.jonaskahn.controllers.user.UserRegisterRequest
import io.github.jonaskahn.entities.User
import io.github.jonaskahn.entities.enums.Status
import io.github.jonaskahn.exception.ShouldNeverOccurException
import io.github.jonaskahn.middlewares.context.UserContextHolder
import io.github.jonaskahn.repositories.UserRepository
import io.github.jonaskahn.services.PagingService
import io.github.jonaskahn.services.authen.PasswordEncoder
import io.hypersistence.tsid.TSID
import jakarta.inject.Inject

internal class UserServiceImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService, PagingService() {

    override fun search(keyword: String?, statuses: Collection<Status>, pageNo: Long): PageData<UserDto> {
        return super.search(
            statuses,
            listOf(),
            pageNo,
            { status, _ -> userRepository.countByKeywordAndStatus(keyword, status) },
            { status, _, offset -> userRepository.searchByKeywordAndStatusAndOffset(keyword, status, offset) }
        )
    }


    override fun createUser(request: UserRegisterRequest) {
        if (userRepository.existsByUsernameOrEmail(request.username, request.email)) {
            throw UserExistException()
        }
        val newUser = User()
        newUser.email = request.email
        newUser.username = request.username ?: request.email
        newUser.fullName = request.name
        newUser.password = passwordEncoder.encode(request.password!!)
        newUser.preferredUsername = TSID.fast().toLong()
        newUser.status = Status.ACTIVATED
        userRepository.create(newUser)
    }

    override fun getCurrentUserInfo(): UserDto {
        val userProfile = UserContextHolder.getCurrentUser()
        val preferredUsername =
            userProfile?.getAttribute(Jwt.Attribute.UID)?.toString() ?: throw ShouldNeverOccurException()
        val user = userRepository.findActivatedUserByPreferredUsername(preferredUsername.toLong())
            ?: throw UserNotFoundException()
        return UserToInfoDtoMapper.INSTANCE.userToUserInfoDto(user)
    }

    override fun getCurrentUserInfoWithExecutor(): UserDto {
        val userProfile = UserContextHolder.getCurrentUser()
        val preferredUsername =
            userProfile?.getAttribute(Jwt.Attribute.UID)?.toString() ?: throw ShouldNeverOccurException()
        return userRepository.findCustomActivatedUserByPreferredUsername(preferredUsername.toLong())
            ?: throw UserNotFoundException()
    }
}