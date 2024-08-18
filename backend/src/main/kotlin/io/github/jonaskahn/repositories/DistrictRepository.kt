package io.github.jonaskahn.repositories

import com.google.inject.ImplementedBy
import io.github.jonaskahn.entities.District
import io.github.jonaskahn.repositories.impl.DistrictRepositoryImpl
import io.github.jonaskahn.services.address.DistrictDto

@ImplementedBy(DistrictRepositoryImpl::class)
interface DistrictRepository {
    fun getDistrictByProvince(id: Int): List<District>

    fun getDistrictById(id: Int): District
}