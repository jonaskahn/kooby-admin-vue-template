package io.github.jonaskahn.controllers.patientrequest

data class PaginationResult<T>(
    val totalItem: Long,
    val totalPage: Int,
    val currentItem: Int,
    val currentPage: Int,
    val data: List<T>
)