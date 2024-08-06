package io.github.jonaskahn.exception

class ValidationException(val data: List<String>?) : RuntimeException("Submitted data is invalid")