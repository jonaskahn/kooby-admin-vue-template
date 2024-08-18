package io.github.jonaskahn.controllers.address

import io.github.jonaskahn.entities.District
import io.github.jonaskahn.entities.Province
import io.github.jonaskahn.services.address.AddressService
import io.github.jonaskahn.services.address.DistrictDto
import io.github.jonaskahn.services.address.ProvinceDto
import io.jooby.annotation.GET
import io.jooby.annotation.Path
import io.jooby.annotation.PathParam
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.inject.Inject

@Tag(name = "Address", description = "APIs for managing addresses and related information")
@Path("/secure")
class AddressController @Inject constructor(private val addressService: AddressService) {
    @GET("/address/province")
    fun getAllProvince(): List<ProvinceDto> {
        return addressService.getAllProvinces()
    }

    @GET("/address/province/{Id}")
    fun getProvinceById(@PathParam("Id") id: Int): Province {
        return addressService.getProvinceById(id)
    }

    @GET("/address/district/province/{provinceId}")
    fun getDistrictByProvince(@PathParam("provinceId") id: Int): List<DistrictDto> {
        return addressService.getAllDistrictsByProvince(id)
    }

    @GET("/address/district/{Id}")
    fun getDistrictByID(@PathParam("Id") id: Int): District {
        return addressService.getDistrictById(id)
    }

}