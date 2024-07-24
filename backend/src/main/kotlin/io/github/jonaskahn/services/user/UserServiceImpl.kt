package io.github.jonaskahn.services.user

import io.github.jonaskahn.constants.Jwt
import io.github.jonaskahn.controller.auth.UserRegisterRequest
import io.github.jonaskahn.entities.User
import io.github.jonaskahn.entities.enums.Status
import io.github.jonaskahn.exception.ShouldNeverOccurException
import io.github.jonaskahn.repositories.UserRepository
import io.github.jonaskahn.services.authen.PasswordEncoder
import io.hypersistence.tsid.TSID
import io.jooby.Context
import jakarta.inject.Inject
import org.pac4j.core.profile.BasicUserProfile

internal class UserServiceImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val context: Context
) : UserService {

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

    override fun getCurrentUserInfo(): UserInfoDto {
        val userProfile = context.getUser<BasicUserProfile>()
        val preferredUsername =
            userProfile?.getAttribute(Jwt.Attribute.UID)?.toString() ?: throw ShouldNeverOccurException()
        val user = userRepository.findActivatedUserByPreferredUsername(preferredUsername.toLong())
            ?: throw UserNotFoundException()
        return UserToInfoDtoMapper.INSTANCE.userToUserInfoDto(user)
    }

    override fun getCurrentUserInfoWithExecutor(): UserInfoDto {
        val userProfile = context.getUser<BasicUserProfile>()
        val preferredUsername =
            userProfile?.getAttribute(Jwt.Attribute.UID)?.toString() ?: throw ShouldNeverOccurException()
        return userRepository.findCustomActivatedUserByPreferredUsername(preferredUsername.toLong())
            ?: throw UserNotFoundException()
    }
}