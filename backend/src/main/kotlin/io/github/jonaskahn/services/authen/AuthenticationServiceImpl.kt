package io.github.jonaskahn.services.authen

import io.github.jonaskahn.constants.Jwt
import io.github.jonaskahn.constants.RedisNameSpace
import io.github.jonaskahn.entities.enums.Status
import io.github.jonaskahn.repositories.UserRepository
import io.github.jonaskahn.services.user.UserInvalidPasswordException
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

    override fun generateToken(username: String, password: String, increaseExpiration: Boolean): String {
        val user = userRepository.findByUsernameOrEmail(username, username) ?: throw UserNotFoundException()
        if (user.status == Status.LOCK) {
            throw UserLockedException()
        }
        if (user.status == Status.DELETED || user.status == Status.INACTIVATED) {
            throw UserNotFoundException()
        }
        if (!passwordEncoder.matches(password, user.password!!)) {
            throw UserInvalidPasswordException()
        }
        val userProfile = CommonProfile()
        userProfile.id = user.id?.toString()
        userProfile.roles = user.roles.toSet()

        val jid = TSID.fast().toString()
        userProfile.addAttribute(Jwt.Attribute.JTI, jid)
        userProfile.addAttribute(Jwt.Attribute.UID, user.preferredUsername.toString())

        val initialExpirationTime = environment.config.getString("jwt.expiration")?.toLong() ?: 3600L
        val expirationTimes =
            if (increaseExpiration) (initialExpirationTime + 60 * 60 * 24 * 15) else initialExpirationTime
        val expirationDate = Date(Date().time + expirationTimes * 1000)
        userProfile.addAttribute(Jwt.Attribute.EXP, expirationDate)

        userProfile.addAttribute(CommonProfileDefinition.DISPLAY_NAME, user.fullName)
        val jwtGenerator = JwtGenerator(
            SecretSignatureConfiguration(environment.config.getString("jwt.salt"))
        )
        jedis.setex(
            RedisNameSpace.getUserTokenExpirationKey(
                user.preferredUsername!!.toString(),
                jid
            ), expirationTimes, expirationDate.toString()
        )
        return jwtGenerator.generate(userProfile)
    }

    override fun logout() {
        val userProfile = context.getUser<BasicUserProfile>()!!
        val jid = userProfile.getAttribute(Jwt.Attribute.JTI).toString()
        val uid = userProfile.getAttribute(Jwt.Attribute.UID).toString()
        val redisKey = RedisNameSpace.getUserTokenExpirationKey(uid, jid)
        jedis.del(redisKey)
    }
}