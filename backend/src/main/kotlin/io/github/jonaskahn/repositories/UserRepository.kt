package io.github.jonaskahn.repositories

import com.google.inject.ImplementedBy
import io.github.jonaskahn.entities.User
import io.github.jonaskahn.repositories.impl.UserRepositoryImpl
import io.github.jonaskahn.services.user.UserDto

@ImplementedBy(UserRepositoryImpl::class)
interface UserRepository {

    fun create(user: User): User

    fun findByUsernameOrEmail(username: String, email: String): User?

    fun findActivatedUserByPreferredUsername(preferredUsername: Long): User?

    fun existsByUsernameOrEmail(username: String?, email: String?): Boolean

    fun findCustomActivatedUserByPreferredUsername(preferredUsername: Long): UserDto?

    fun countByKeywordAndStatus(keyword: String?, status: Collection<Int>): Long

    fun searchByKeywordAndStatusAndOffset(keyword: String?, status: Collection<Int>, offset: Long): Collection<UserDto>
}