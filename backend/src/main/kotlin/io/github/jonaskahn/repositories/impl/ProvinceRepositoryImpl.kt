package io.github.jonaskahn.repositories.impl

import io.github.jonaskahn.entities.Province
import io.github.jonaskahn.repositories.ProvinceRepository
import io.jooby.Context
import jakarta.inject.Inject
import jakarta.persistence.EntityManager


class ProvinceRepositoryImpl @Inject constructor(
    private val entityManager: EntityManager,
    private val context: Context
) : BaseRepositoryImpl<Province, Long>(entityManager, Province::class.java, context), ProvinceRepository  {

    override fun findAll(): List<Province> {
        return super.findAll()
    }
}