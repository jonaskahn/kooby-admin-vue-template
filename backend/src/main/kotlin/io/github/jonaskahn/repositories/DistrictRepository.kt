package io.github.jonaskahn.repositories

import com.google.inject.ImplementedBy
import io.github.jonaskahn.entities.District
import io.github.jonaskahn.repositories.impl.DistrictRepositoryImpl

@ImplementedBy(DistrictRepositoryImpl::class)
interface DistrictRepository {
    fun getDistrictByProvince(id: Int): List<District>
}