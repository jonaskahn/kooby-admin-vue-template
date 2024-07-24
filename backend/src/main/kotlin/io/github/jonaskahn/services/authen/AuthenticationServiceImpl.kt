package io.github.jonaskahn.services.authen

import io.github.jonaskahn.constants.Jwt
import io.github.jonaskahn.constants.RedisNameSpace
import io.github.jonaskahn.entities.enums.Status
import io.github.jonaskahn.repositories.UserRepository
import io.github.jonaskahn.services.user.UserLockedException
import io.github.jonaskahn.services.user.UserNotFoundException
import io.hypersistence.tsid.TSID
import io.jooby.Context
import io.jooby.Environment
import jakarta.inject.Inject
import org.pac4j.core.profile.BasicUserProfile
import org.pac4j.core.profile.CommonProfile
import org.pac4j.core.profile.definition.CommonProfileDefinition
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration
import org.pac4j.jwt.profile.JwtGenerator
import redis.clients.jedis.JedisPooled
import java.util.*

internal class AuthenticationServiceImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val environment: Environment,
    private val context: Context,
    private val jedis: JedisPooled,
) : AuthenticationService {
    override fun generateToken(username: String, password: String): String {
        val users = userRepository.findByUsernameOrEmail(username) ?: throw UserNotFoundException()
        if (users.status == Status.LOCK || users.status == Status.INACTIVATED) {
            throw UserLockedException()
        }
        if (users.status == Status.DELETED) {
            throw UserNotFoundException()
        }
        if (!passwordEncoder.matches(password, users.password!!)) {
            throw UsernamePasswordException("app.users.exception.user-password-not-correct")
        }
        val profile = CommonProfile()
        profile.id = users.id?.toString()
        profile.roles = users.roles.toSet()

        val jid = TSID.fast().toString()
        profile.addAttribute(Jwt.Attribute.JTI, jid)
        profile.addAttribute(Jwt.Attribute.UID, users.preferredUsername.toString())

        val expirationTimeInSeconds = environment.config.getString("jwt.expiration")?.toLong() ?: 3600L
        val expirationDate = Date(Date().time + expirationTimeInSeconds * 1000)
        profile.addAttribute(Jwt.Attribute.EXP, expirationDate)

        profile.addAttribute(CommonProfileDefinition.DISPLAY_NAME, users.fullName)
        val jwtGenerator = JwtGenerator(
            SecretSignatureConfiguration(environment.config.getString("jwt.salt"))
        )
        jedis.setex(
            RedisNameSpace.getUserTokenExpirationKey(
                users.preferredUsername!!.toString(),
                jid
            ), expirationTimeInSeconds, expirationDate.toString()
        )
        return jwtGenerator.generate(profile)
    }

    override fun logout() {
        val profile = context.getUser<BasicUserProfile>()!!
        val jid = profile.getAttribute(Jwt.Attribute.JTI).toString()
        val uid = profile.getAttribute(Jwt.Attribute.UID).toString()
        val redisKey = RedisNameSpace.getUserTokenExpirationKey(uid, jid)
        jedis.del(redisKey)
    }
}