package io.github.jonaskahn.services

import io.github.jonaskahn.assistant.PageData
import io.github.jonaskahn.constants.Defaults.Pageable.DEFAULT_PAGE_SIZE
import io.github.jonaskahn.entities.enums.Status
import kotlin.math.ceil

abstract class PagingService {

    fun <T> search(
        statuses: Collection<Status> = listOf(),
        pageNo: Long,
        count: (statuses: Collection<Int>) -> Long,
        lookup: (statuses: Collection<Int>, offset: Long) -> Collection<T>
    ): PageData<T> {
        val statusValues = statuses.ifEmpty { Status.entries }.map { it.id }
        val totalItems = count(statusValues)
        val totalPages = (ceil(totalItems * 1F / DEFAULT_PAGE_SIZE)).toLong()
        val calculatedPageNumber = calculatePageNumber(pageNo, totalPages)
        val offset = (calculatedPageNumber - 1) * DEFAULT_PAGE_SIZE
        val records = if (totalItems > 0) lookup(statusValues, offset) else listOf()
        return PageData(
            data = records,
            currentPage = if (totalPages == 0L) 0L else calculatedPageNumber,
            totalPages = totalPages,
            totalItems = totalItems
        )
    }

    private fun calculatePageNumber(pageNumber: Long, totalPages: Long): Long {
        return if (totalPages < 1) {
            1L
        } else if (pageNumber <= 1) {
            1L
        } else if (pageNumber >= totalPages) {
            totalPages
        } else {
            pageNumber
        }
    }
}