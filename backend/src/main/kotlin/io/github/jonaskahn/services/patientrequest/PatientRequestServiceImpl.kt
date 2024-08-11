package io.github.jonaskahn.services.patientrequest

import io.github.jonaskahn.controllers.patientrequest.PaginationResult
import io.github.jonaskahn.controllers.patientrequest.PatientRequestForm
import io.github.jonaskahn.entities.PatientRequest
import io.github.jonaskahn.repositories.PatientRequestRepository
import io.jooby.Context
import jakarta.inject.Inject

internal class PatientRequestServiceImpl @Inject constructor(
    private val patientRequestRepository: PatientRequestRepository,
    private val context: Context
): PatientRequestService {
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

    override fun findByKeywordWithPagination(keyword: String, offset: Int, limit: Int): PaginationResult<PatientRequestDto> {
        return patientRequestRepository.findByKeywordWithPagination(keyword, offset, limit)
    }
}