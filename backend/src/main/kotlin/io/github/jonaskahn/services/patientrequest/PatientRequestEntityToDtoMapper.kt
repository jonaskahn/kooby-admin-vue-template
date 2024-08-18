package io.github.jonaskahn.services.patientrequest

import io.github.jonaskahn.controllers.patientrequest.PaginationResult
import io.github.jonaskahn.entities.Delivery
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
        Mapping(source = "patientRequest.id", target = "patientRequestId"),
        Mapping(source = "patientRequest.numberOrder", target = "numberOrder"),

        Mapping(source = "delivery.id", target = "deliveryId"),
        Mapping(source = "delivery.orderNumber", target = "orderNumber"),
    )
    fun toDto(patientRequest: PatientRequest, delivery: Delivery?): PatientRequestDto
}