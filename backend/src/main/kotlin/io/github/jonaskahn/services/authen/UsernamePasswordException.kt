package io.github.jonaskahn.services.authen

import io.github.jonaskahn.exception.AuthenticationException

class UsernamePasswordException(override val message: String?) : AuthenticationException(message)