package io.github.jonaskahn.services.user

import io.github.jonaskahn.exception.LogicException

open class UserNotFoundException : LogicException("app.users.exception.user-not-found")