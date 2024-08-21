package io.github.jonaskahn.services.patientrequest

import com.google.inject.ImplementedBy
import io.github.jonaskahn.assistant.PageData
import io.github.jonaskahn.controllers.patientrequest.PaginationResult
import io.github.jonaskahn.controllers.patientrequest.PatientRequestForm
import io.github.jonaskahn.entities.enums.State

@ImplementedBy(PatientRequestServiceImpl::class)
interface PatientRequestService {
    fun createRequest(request: PatientRequestForm)

    fun search(
        keyword: String? = null,
        states: Collection<State> = listOf(State.PENDING),
        pageNo: Long = 0L,
    ): PageData<PatientRequestDto>
}