package io.github.jonaskahn.repositories

import io.github.jonaskahn.assistant.query.JpaQueryExecutor
import io.github.jonaskahn.entities.User
import io.github.jonaskahn.entities.enums.StatusCode
import io.github.jonaskahn.services.user.UserInfoDto
import io.jooby.Context
import jakarta.inject.Inject
import jakarta.persistence.EntityManager

class UserRepositoryImpl @Inject constructor(
    context: Context,
    private val em: EntityManager
) : BaseRepository<User, Long>(context, em), UserRepository {

    override fun findByUsernameOrEmail(username: String): User? {
        val query =
            em.createQuery("select u from users u where username = :username or email = :username", User::class.java)
        query.setParameter("username", username)
        return query.singleResult
    }

    override fun findActivatedUserByPreferredUsername(preferredUsername: Long): User? {
        val query =
            em.createQuery(
                "select u from users u where preferredUsername = :preferredUsername and status = ${StatusCode.ACTIVATED}",
                User::class.java
            )
        query.setParameter("preferredUsername", preferredUsername)
        return query.singleResult
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
        val query = em.createQuery("select exists ($sql)", Boolean::class.java)
        params.forEach { (k, v) -> query.setParameter(k, v) }
        return query.singleResult
    }

    override fun findCustomActivatedUserByPreferredUsername(preferredUsername: Long): UserInfoDto? {
        val query =
            em.createNativeQuery("select * from users where preferred_username = :preferredUsername and status = ${StatusCode.ACTIVATED}")
        return JpaQueryExecutor.builder<UserInfoDto>()
            .with(query, mutableMapOf("preferredUsername" to preferredUsername))
            .map(UserInfoDto::class.java)
            .getSingleResult()
    }
}