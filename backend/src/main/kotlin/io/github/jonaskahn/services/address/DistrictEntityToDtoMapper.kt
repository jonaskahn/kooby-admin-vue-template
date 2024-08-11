package io.github.jonaskahn.services.address

import io.github.jonaskahn.entities.District
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface DistrictEntityToDtoMapper {
    companion object{
        var INSTANCE: DistrictEntityToDtoMapper = Mappers.getMapper(DistrictEntityToDtoMapper::class.java)
    }

    fun districtToDistrictDto(district: District): DistrictDto
}

fun DistrictEntityToDtoMapper.safeDistrictToDistrictDto(district: District?): DistrictDto? {
    return district?.let { this.districtToDistrictDto(it) }
}