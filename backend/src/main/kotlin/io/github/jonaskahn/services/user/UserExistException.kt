package io.github.jonaskahn.services.user

import io.github.jonaskahn.exception.LogicException

class UserExistException : LogicException("User is existed")