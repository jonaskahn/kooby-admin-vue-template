package io.github.jonaskahn.exception

open class LogicException(
    override val cause: Throwable? = null,
    override val message: String,
    vararg val variables: Any?
) :
    RuntimeException(message, cause) {
    constructor(message: String) : this(cause = null, message = message)
    constructor(message: String, vararg variables: Any?) : this(cause = null, message = message, variables = variables)
}