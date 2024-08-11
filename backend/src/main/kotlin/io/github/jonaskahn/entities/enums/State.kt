package io.github.jonaskahn.entities.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class State(@get:JsonValue val id: Int, val description: String) {
    PENDING(Code.PENDING, "app.common.enum.state.pending"),
    ASSIGNED(Code.ASSIGNED, "app.common.enum.state.assigned"),
    IN_PROGRESS(Code.IN_PROGRESS, "app.common.enum.state.in_progress"),
    MANAGER_APPROVED(Code.MANAGER_APPROVED, "app.common.enum.state.manager_approved"),
    DIRECTOR_APPROVED(Code.DIRECTOR_APPROVED, "app.common.enum.state.director_approved"),
    REJECTED(Code.REJECTED, "app.common.enum.state.rejected"),
    COMPLETE(Code.COMPLETE, "app.common.enum.state.complete");

    companion object {
        private val valuesAsMap = State.entries.associateBy { it.id }
        fun of(id: Int): State {
            return valuesAsMap.getOrElse(id) {
                throw NoSuchElementException("State [$id] is not existed")
            }
        }
    }

    object Code {
        const val PENDING = 0
        const val ASSIGNED = 1
        const val IN_PROGRESS = 2
        const val MANAGER_APPROVED = 3
        const val DIRECTOR_APPROVED = 4
        const val REJECTED = 5
        const val COMPLETE = 6
    }
}