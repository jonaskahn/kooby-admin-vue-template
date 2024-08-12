package io.github.jonaskahn.assistant


data class PageData<T>(
    val data: Collection<T>,
    val currentPage: Long,
    val totalPages: Long,
    val totalItems: Long,
) {
    companion object {
        fun <T> empty(): PageData<T> {
            return PageData(
                data = listOf(),
                currentPage = 0,
                totalPages = 0,
                totalItems = 0
            )
        }
    }
}
