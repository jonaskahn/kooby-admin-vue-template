package io.github.jonaskahn.services.patientrequest

import io.github.jonaskahn.controllers.patientrequest.PaginationResult
import io.github.jonaskahn.entities.Assignment
import io.github.jonaskahn.entities.PatientRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface PatientRequestEntityToDtoMapper {
    companion object{
        var INSTANCE: PatientRequestEntityToDtoMapper = Mappers.getMapper(PatientRequestEntityToDtoMapper::class.java)
    }
    @Mappings(
        Mapping(source = "assignment.id", target = "idAssignment"),

        Mapping(source = "patientRequest.status", target = "requestStatus"),
        Mapping(source = "patientRequest.id", target = "idPatientRequest"),
    )
    fun toDto(patientRequest: PatientRequest, assignment: Assignment?): PatientRequestDto
}