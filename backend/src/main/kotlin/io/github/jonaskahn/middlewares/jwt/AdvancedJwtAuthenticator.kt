package io.github.jonaskahn.middlewares.jwt

import com.nimbusds.jwt.JWT
import io.github.jonaskahn.constants.Jwt
import io.github.jonaskahn.constants.RedisNameSpace
import io.github.jonaskahn.exception.AuthorizationException
import org.pac4j.core.context.WebContext
import org.pac4j.core.context.session.SessionStore
import org.pac4j.core.credentials.TokenCredentials
import org.pac4j.jwt.config.signature.SignatureConfiguration
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator
import redis.clients.jedis.JedisPooled
import java.text.ParseException
import java.util.*

class AdvancedJwtAuthenticator(private val redis: JedisPooled, signatureConfiguration: SignatureConfiguration) :
    JwtAuthenticator(signatureConfiguration) {
    @Throws(ParseException::class)
    override fun createJwtProfile(
        credentials: TokenCredentials, jwt: JWT, context: WebContext?,
        sessionStore: SessionStore?
    ) {
        val jwtId = jwt.jwtClaimsSet.jwtid.toString()
        val uid = jwt.jwtClaimsSet.claims[Jwt.Attribute.UID].toString()
        if (!redis.exists(RedisNameSpace.getUserTokenExpirationKey(uid, jwtId))) {
            throw AuthorizationException()
        }
        super.createJwtProfile(credentials, jwt, context, sessionStore)
    }
}