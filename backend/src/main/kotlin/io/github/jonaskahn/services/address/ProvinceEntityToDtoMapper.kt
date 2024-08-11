package io.github.jonaskahn.services.address

import io.github.jonaskahn.entities.Province
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface ProvinceEntityToDtoMapper {
    companion object {
        var INSTANCE: ProvinceEntityToDtoMapper = Mappers.getMapper(ProvinceEntityToDtoMapper::class.java)
    }
    fun provinceToProvinceDto(province: Province): ProvinceDto
}