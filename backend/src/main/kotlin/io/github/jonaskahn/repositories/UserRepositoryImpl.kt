package io.github.jonaskahn.repositories

import io.github.jonaskahn.assistant.query.JpaQueryExecutor
import io.github.jonaskahn.entities.User
import io.github.jonaskahn.entities.enums.Status
import io.github.jonaskahn.services.user.UserDto
import io.jooby.Context
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import org.slf4j.LoggerFactory

class UserRepositoryImpl @Inject constructor(
    private val entityManager: EntityManager,
    context: Context
) : BaseRepositoryImpl<User, Long>(entityManager, User::class.java, context), UserRepository {

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


    companion object {
        private val log = LoggerFactory.getLogger(UserRepositoryImpl::class.java)
    }
}