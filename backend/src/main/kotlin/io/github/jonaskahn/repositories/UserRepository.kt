package io.github.jonaskahn.repositories

import com.google.inject.ImplementedBy
import io.github.jonaskahn.entities.User
import io.github.jonaskahn.services.user.UserInfoDto

@ImplementedBy(UserRepositoryImpl::class)
interface UserRepository {

    fun create(user: User)

    fun update(user: User)

    fun findByUsernameOrEmail(username: String): User?

    fun findActivatedUserByPreferredUsername(preferredUsername: Long): User?

    fun existsByUsernameOrEmail(username: String?, email: String?): Boolean

    fun findCustomActivatedUserByPreferredUsername(preferredUsername: Long): UserInfoDto?
}