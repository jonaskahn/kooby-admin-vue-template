package io.github.jonaskahn.controllers.user

import io.github.jonaskahn.entities.enums.Status

data class UserSearchRequest(
    val keyword: String? = null,
    val statuses: Collection<Status> = listOf(Status.ACTIVATED),
    val pageNo: Long = 0L
)