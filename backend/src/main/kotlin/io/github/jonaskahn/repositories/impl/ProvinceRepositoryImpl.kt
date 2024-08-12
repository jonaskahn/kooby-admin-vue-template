package io.github.jonaskahn.repositories.impl

import io.github.jonaskahn.entities.Province
import io.github.jonaskahn.repositories.AbstractBaseRepository
import io.github.jonaskahn.repositories.ProvinceRepository
import io.jooby.Context
import jakarta.inject.Inject
import jakarta.persistence.EntityManager


class ProvinceRepositoryImpl @Inject constructor(
    override val entityManager: EntityManager
) : AbstractBaseRepository(entityManager), ProvinceRepository  {

     override fun findAll(): List<Province> {
        return listOf()
    }
}