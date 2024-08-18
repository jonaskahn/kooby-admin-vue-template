package io.github.jonaskahn.repositories.impl

import io.github.jonaskahn.entities.Department
import io.github.jonaskahn.entities.enums.Status
import io.github.jonaskahn.repositories.AbstractBaseRepository
import io.github.jonaskahn.repositories.DepartmentRepository
import jakarta.inject.Inject
import jakarta.persistence.EntityManager

class DepartmentRepositoryImpl @Inject constructor(
    override val entityManager: EntityManager,
): AbstractBaseRepository(entityManager), DepartmentRepository {
    override fun getAll(): List<Department> {
        val query = entityManager.createQuery("select d from Department d where d.status=: status", Department::class.java)
        query.setParameter("status", Status.ACTIVATED)
        return query.resultList
    }

    override fun getById(id: Int): Department {
        val query = entityManager.createQuery("select d from Department d where d.id=:id and d.status=: status", Department::class.java)
        query.setParameter("id", id)
        query.setParameter("status", Status.ACTIVATED)
        return query.singleResult
    }

    override fun getByName(name: String): Department {
        val query = entityManager.createQuery("select d from Department d where d.name=:name and d.status=: status", Department::class.java)
        query.setParameter("name", name)
        query.setParameter("status", Status.ACTIVATED)
        return query.singleResult
    }
}