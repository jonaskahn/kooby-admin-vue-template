package io.github.jonaskahn.exception

open class AuthenticationException(override val message: String?) :
    RuntimeException(message ?: "Authentication Exception")