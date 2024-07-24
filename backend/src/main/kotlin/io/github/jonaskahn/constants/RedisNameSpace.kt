package io.github.jonaskahn.constants

object RedisNameSpace {
    private const val USER_TOKEN_EXPIRATION = "UserTokenExpiration"

    fun getUserTokenExpirationKey(preferredUsername: String, jid: String): String {
        return "$USER_TOKEN_EXPIRATION:${preferredUsername}:${jid}"
    }
}