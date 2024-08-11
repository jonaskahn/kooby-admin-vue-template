package io.github.jonaskahn.services.patientrequest

import io.github.jonaskahn.controllers.patientrequest.PaginationResult
import io.github.jonaskahn.entities.PatientRequest
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface PatientRequestEntityToDtoMapper {
    companion object{
        var INSTANCE: PatientRequestEntityToDtoMapper = Mappers.getMapper(PatientRequestEntityToDtoMapper::class.java)
    }
    fun toDto(patientRequest: PatientRequest): PatientRequestDto
}