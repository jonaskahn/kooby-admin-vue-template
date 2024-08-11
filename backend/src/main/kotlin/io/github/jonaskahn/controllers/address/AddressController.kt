package io.github.jonaskahn.controllers.address

import io.github.jonaskahn.services.address.AddressService
import io.github.jonaskahn.services.address.DistrictDto
import io.github.jonaskahn.services.address.ProvinceDto
import io.jooby.annotation.GET
import io.jooby.annotation.Path
import io.jooby.annotation.PathParam
import jakarta.inject.Inject

@Path("/secure")
class AddressController @Inject constructor(private val addressService: AddressService) {
    @GET("/address/province")
    fun getAllProvince(): List<ProvinceDto> {
        return addressService.getAllProvinces()
    }

    @GET("/address/district/{provinceId}")
    fun getDistrict(@PathParam("provinceId") id: Int): List<DistrictDto> {
        return addressService.getAllDistrictsByProvince(id)
    }
}