package io.github.jonaskahn.controllers.patientrequest


import io.github.jonaskahn.entities.PatientRequest
import io.github.jonaskahn.services.patientrequest.PatientRequestDto
import io.github.jonaskahn.services.patientrequest.PatientRequestService
import io.jooby.annotation.POST
import io.jooby.annotation.Path
import jakarta.inject.Inject

@Path("secure")
class PatientRequestController @Inject constructor(private val patientRequestService: PatientRequestService) {

    @POST("/patientRequest/create")
    fun createNewRequest(request: PatientRequestForm) {
        return patientRequestService.createRequest(request)
    }

    @POST("/patientRequest/find")
    fun findPatientRequest(request: SearchRequestForm): PaginationResult<PatientRequestDto> {
        return patientRequestService.findByKeywordWithPagination(request.keyword, request.offset, request.limit)
    }
}