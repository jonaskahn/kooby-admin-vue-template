package io.github.jonaskahn.services.address

import com.google.inject.ImplementedBy

@ImplementedBy(AddressServiceImpl::class)
interface AddressService {
    fun getAllProvinces(): List<ProvinceDto>

    fun getAllDistrictsByProvince(id: Int): List<DistrictDto>
}