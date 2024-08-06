package io.github.jonaskahn.entities.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class Status(@get:JsonValue val id: Int, val description: String) {
    INACTIVATED(Code.INACTIVATED, "app.common.enum.status.inactive"),
    ACTIVATED(Code.ACTIVATED, "app.common.enum.status.active"),
    LOCK(Code.LOCK, "app.common.enum.status.lock"),
    DELETED(Code.DELETED, "app.common.enum.status.deleted");

    companion object {
        private val valuesAsMap = entries.associateBy { it.id }
        fun of(id: Int): Status {
            return valuesAsMap.getOrElse(id) {
                throw NoSuchElementException("Status [$id] is not existed")
            }
        }
    }

    object Code {
        const val ACTIVATED = 1
        const val INACTIVATED = 2
        const val LOCK = 3
        const val DELETED = 9
    }
}
