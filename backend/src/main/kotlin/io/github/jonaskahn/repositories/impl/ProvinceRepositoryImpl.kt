package io.github.jonaskahn.repositories.impl

import io.github.jonaskahn.entities.Province
import io.github.jonaskahn.entities.enums.Status
import io.github.jonaskahn.repositories.AbstractBaseRepository
import io.github.jonaskahn.repositories.ProvinceRepository
import jakarta.inject.Inject
import jakarta.persistence.EntityManager


class ProvinceRepositoryImpl @Inject constructor(
    override val entityManager: EntityManager
) : AbstractBaseRepository(entityManager), ProvinceRepository  {

     override fun findAll(): List<Province> {
         val query = entityManager.createQuery(
             "SELECT p FROM Province p WHERE p.status = :status", Province::class.java
         )
         query.setParameter("status", Status.ACTIVATED)
         return query.resultList
    }

    override fun getProvinceById(id: Int): Province {
        val query = entityManager.createQuery(
            "SELECT p FROM Province p WHERE p.id = :id and p.status =:status",Province::class.java
        )
        query.setParameter("status", Status.ACTIVATED)
        query.setParameter("id", id)

        return query.singleResult
    }
}