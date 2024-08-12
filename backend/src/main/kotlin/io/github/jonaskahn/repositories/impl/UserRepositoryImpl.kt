package io.github.jonaskahn.repositories

import io.github.jonaskahn.assistant.query.JpaQueryExecutor
import io.github.jonaskahn.constants.Defaults
import io.github.jonaskahn.entities.User
import io.github.jonaskahn.entities.enums.Status
import io.github.jonaskahn.services.user.UserDto
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory

class UserRepositoryImpl @Inject constructor(
    override val entityManager: EntityManager
) : AbstractBaseRepository(entityManager), UserRepository {
    override fun create(user: User): User {
       entityManager.persist(user)
        return user
    }

    override fun findByUsernameOrEmail(username: String, email: String): User? {
        val query =
            entityManager.createQuery(
                "select u from users u where username = :username or email = :email",
                User::class.java
            )
        query.setParameter("username", username)
        query.setParameter("email", email)
        return try {
            query.singleResult
        } catch (e: Exception) {
            log.warn("Could not find the users with given username or email", e)
            null
        }
    }

    override fun findActivatedUserByPreferredUsername(preferredUsername: Long): User? {
        val query =
            entityManager.createQuery(
                "select u from users u where preferredUsername = :preferredUsername and status = ${Status.Code.ACTIVATED}",
                User::class.java
            )
        query.setParameter("preferredUsername", preferredUsername)
        return try {
            query.singleResult
        } catch (e: Exception) {
            log.warn("Could not find the users with given preferredUsername", e)
            null
        }
    }

    override fun existsByUsernameOrEmail(username: String?, email: String?): Boolean {
        if (username == null && email == null) return false
        val sql = StringBuilder("SELECT 1 FROM users u WHERE 1 = 1 ")
        val params = mutableMapOf<String, String>()
        if (username != null) {
            sql.append(" AND u.username = :username")
            params["username"] = username
        }
        if (email != null) {
            sql.append(" AND u.email = :email")
            params["email"] = email
        }
        val query = entityManager.createQuery("select exists ($sql)", Boolean::class.java)
        params.forEach { (k, v) -> query.setParameter(k, v) }
        return query.singleResult
    }

    override fun findCustomActivatedUserByPreferredUsername(preferredUsername: Long): UserDto? {
        val query =
            entityManager.createNativeQuery("select * from users where preferred_username = :preferredUsername and status = ${Status.Code.ACTIVATED}")
        return JpaQueryExecutor.builder<UserDto>()
            .with(query, mutableMapOf("preferredUsername" to preferredUsername))
            .map(UserDto::class.java)
            .getSingleResult()
    }

    override fun countByKeywordAndStatus(keyword: String?, status: Collection<Int>): Long {
        return count(true, "select count(1) from users u where 1 = 1 ") { builder, params ->
            queryBuilderByKeywordAndStatus(keyword, builder, params, status)
        }
    }

    private fun queryBuilderByKeywordAndStatus(
        keyword: String?,
        builder: StringBuilder,
        params: MutableMap<String, Any>,
        status: Collection<Int>
    ) {
        if (StringUtils.isNotBlank(keyword)) {
            builder.append(" AND (u.username like %:keyword% or u.email like %:keyword%)")
            params["keyword"] = keyword!!
        }
        if (status.isNotEmpty()) {
            builder.append(" AND u.status in (:status)")
            params["status"] = status
        }
    }

    override fun searchByKeywordAndStatusAndOffset(
        keyword: String?,
        status: Collection<Int>,
        offset: Long
    ): Collection<UserDto> {
        return search(true, "select * from users u where 1 = 1 ", UserDto::class.java) { builder, params ->
            queryBuilderByKeywordAndStatus(keyword, builder, params, status)
            builder.append(" limit :limit offset :offset")
            params["limit"] = Defaults.Pageable.DEFAULT_PAGE_SIZE
            params["offset"] = offset
        }
    }


    companion object {
        private val log = LoggerFactory.getLogger(UserRepositoryImpl::class.java)
    }
}