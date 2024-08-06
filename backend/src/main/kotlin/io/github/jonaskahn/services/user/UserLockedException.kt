package io.github.jonaskahn.services.user

import io.github.jonaskahn.exception.LogicException

class UserLockedException : LogicException("app.users.exception.user-locked")