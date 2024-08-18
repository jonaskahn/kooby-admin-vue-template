package io.github.jonaskahn.controllers.patientrequest

import io.github.jonaskahn.entities.enums.State

data class SearchRequestForm(
    val keyword: String? = null,
    val states: Collection<State> = listOf(State.PENDING),
    val pageNo: Long =0L
)