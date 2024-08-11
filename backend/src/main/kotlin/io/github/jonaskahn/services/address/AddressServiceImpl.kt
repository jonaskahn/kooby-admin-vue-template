package io.github.jonaskahn.services.address

import io.github.jonaskahn.repositories.DistrictRepository
import io.github.jonaskahn.repositories.ProvinceRepository
import io.jooby.Context
import jakarta.inject.Inject

class AddressServiceImpl @Inject constructor(
    private val provinceRepository: ProvinceRepository,
    private val districtRepository: DistrictRepository,
    private val context: Context
): AddressService {
    override fun getAllProvinces(): List<ProvinceDto> {
        return provinceRepository.findAll()
            .map { ProvinceEntityToDtoMapper.INSTANCE.provinceToProvinceDto(it) }
    }


    override fun getAllDistrictsByProvince(id: Int): List<DistrictDto> {
        return districtRepository.getDistrictByProvince(id)
            .map { DistrictEntityToDtoMapper.INSTANCE.districtToDistrictDto(it) }
    }
}