package io.github.jonaskahn.middlewares.jwt

import com.nimbusds.jwt.JWT
import io.github.jonaskahn.constants.Jwt
import io.github.jonaskahn.constants.RedisNameSpace
import io.github.jonaskahn.exception.AuthorizationException
import io.github.jonaskahn.middlewares.context.UserContextHolder
import org.pac4j.core.context.CallContext
import org.pac4j.core.credentials.TokenCredentials
import org.pac4j.jwt.config.signature.SignatureConfiguration
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator
import redis.clients.jedis.JedisPooled
import java.text.ParseException

class AdvancedJwtAuthenticator(private val redis: JedisPooled, signatureConfiguration: SignatureConfiguration) :
    JwtAuthenticator(signatureConfiguration) {
    @Throws(ParseException::class)

    override fun createJwtProfile(
        ctx: CallContext,
        credentials: TokenCredentials,
        jwt: JWT
    ) {
        val jwtId = jwt.jwtClaimsSet.jwtid.toString()
        val uid = jwt.jwtClaimsSet.claims[Jwt.Attribute.UID].toString()
        if (!redis.exists(RedisNameSpace.getUserTokenExpirationKey(uid, jwtId))) {
            throw AuthorizationException()
        }
        super.createJwtProfile(ctx, credentials, jwt)
        UserContextHolder.setCurrentUserInfo(credentials.userProfile)
    }
}