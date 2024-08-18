package io.github.jonaskahn.services.address

import com.google.inject.ImplementedBy
import io.github.jonaskahn.entities.District
import io.github.jonaskahn.entities.Province

@ImplementedBy(AddressServiceImpl::class)
interface AddressService {

    fun getAllProvinces(): List<ProvinceDto>

    fun getAllDistrictsByProvince(id: Int): List<DistrictDto>

    fun getDistrictById(id: Int): District

    fun getProvinceById(id: Int): Province
}