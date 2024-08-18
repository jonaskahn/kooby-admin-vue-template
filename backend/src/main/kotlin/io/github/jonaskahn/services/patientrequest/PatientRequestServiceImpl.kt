package io.github.jonaskahn.services.patientrequest

import io.github.jonaskahn.assistant.PageData
import io.github.jonaskahn.controllers.patientrequest.PaginationResult
import io.github.jonaskahn.controllers.patientrequest.PatientRequestForm
import io.github.jonaskahn.entities.PatientRequest
import io.github.jonaskahn.entities.enums.State
import io.github.jonaskahn.repositories.PatientRequestRepository
import io.github.jonaskahn.services.PagingService
import io.jooby.Context
import jakarta.inject.Inject

internal class PatientRequestServiceImpl @Inject constructor(
    private val patientRequestRepository: PatientRequestRepository,
): PatientRequestService, PagingService() {
    override fun createRequest(request: PatientRequestForm) {
        val newRequest = PatientRequest()
        newRequest.numberOrder = request.numberOrder
        newRequest.patientNumber = request.patientNumber
        newRequest.medicineCode = request.medicineCode
        newRequest.patientName = request.patientName
        newRequest.department = request.department
        newRequest.saveNumber= request.saveNumber
        newRequest.inDate = request.inDate
        newRequest.outDate = request.outDate
        newRequest.receptionDate = request.receptionDate
        newRequest.appointmentPatientDate= request.appointmentPatientDate
        newRequest.copyQuantity = request.copyQuantity
        newRequest.copyCost = request.copyCost
        newRequest.note = request.note
        patientRequestRepository.create(newRequest)
    }

    override fun search(
        keyword: String?,
        states: Collection<State>,
        pageNo: Long
    ): PageData<PatientRequestDto> {
        return super.search(
            listOf(),
            states,
            pageNo,
            {_, state -> patientRequestRepository.countByKeywordAndState(keyword, state)},
            {_, state, offset -> patientRequestRepository.searchByKeywordAndStateAndOffset(keyword, state, offset)}

        )
    }
}