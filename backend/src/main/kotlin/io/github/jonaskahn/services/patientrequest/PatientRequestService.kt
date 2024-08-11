package io.github.jonaskahn.services.patientrequest

import com.google.inject.ImplementedBy
import io.github.jonaskahn.controllers.patientrequest.PaginationResult
import io.github.jonaskahn.controllers.patientrequest.PatientRequestForm

@ImplementedBy(PatientRequestServiceImpl::class)
interface PatientRequestService {
    fun createRequest(request: PatientRequestForm)

    fun findByKeywordWithPagination(keyword: String, offset: Int, limit: Int): PaginationResult<PatientRequestDto>
}