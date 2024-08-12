package io.github.jonaskahn.repositories.impl

import io.github.jonaskahn.entities.District
import io.github.jonaskahn.entities.enums.Status
import io.github.jonaskahn.repositories.AbstractBaseRepository
import io.github.jonaskahn.repositories.DistrictRepository
import io.jooby.Context
import jakarta.inject.Inject
import jakarta.persistence.EntityManager

class DistrictRepositoryImpl @Inject constructor(
    override val entityManager: EntityManager,
): AbstractBaseRepository(entityManager), DistrictRepository {

    override fun getDistrictByProvince(id: Int): List<District> {
        val query = entityManager.createQuery(
            "SELECT d FROM District d WHERE d.idProvince = :idProvince and d.status = :status", District::class.java
        )
        query.setParameter("idProvince", id)
        query.setParameter("status", Status.ACTIVATED)
        return query.resultList
    }

}