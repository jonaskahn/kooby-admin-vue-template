package io.github.jonaskahn.controllers.patientrequest


import io.github.jonaskahn.assistant.PageData
import io.github.jonaskahn.entities.PatientRequest
import io.github.jonaskahn.services.patientrequest.PatientRequestDto
import io.github.jonaskahn.services.patientrequest.PatientRequestService
import io.jooby.annotation.POST
import io.jooby.annotation.PUT
import io.jooby.annotation.Path
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.inject.Inject

@Tag(name = "Patient Request", description = "APIs for managing patient requests")
@Path("secure")
class PatientRequestController @Inject constructor(private val patientRequestService: PatientRequestService) {

    @POST("/patientRequest/create")
    fun createNewRequest(request: PatientRequestForm) {
        return patientRequestService.createRequest(request)
    }


    @POST("/patientRequest/search")
    fun searchPatientRequest(request: SearchRequestForm): PageData<PatientRequestDto> {
        return patientRequestService.search(request.keyword, request.states, request.pageNo)
    }
}