package io.github.jonaskahn.services.address

import io.github.jonaskahn.entities.District
import io.github.jonaskahn.entities.Province
import io.github.jonaskahn.repositories.DistrictRepository
import io.github.jonaskahn.repositories.ProvinceRepository
import jakarta.inject.Inject

class AddressServiceImpl @Inject constructor(
    private val provinceRepository: ProvinceRepository,
    private val districtRepository: DistrictRepository
): AddressService {
    override fun getAllProvinces(): List<ProvinceDto> {
        return provinceRepository.findAll()
            .map { ProvinceEntityToDtoMapper.INSTANCE.provinceToProvinceDto(it) }
    }


    override fun getAllDistrictsByProvince(id: Int): List<DistrictDto> {
        return districtRepository.getDistrictByProvince(id)
            .map { DistrictEntityToDtoMapper.INSTANCE.districtToDistrictDto(it) }
    }

    override fun getDistrictById(id: Int): District {
        return districtRepository.getDistrictById(id)
    }

    override fun getProvinceById(id: Int): Province {
        return provinceRepository.getProvinceById(id)
    }
}