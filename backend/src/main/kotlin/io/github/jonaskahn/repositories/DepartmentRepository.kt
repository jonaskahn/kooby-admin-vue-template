package io.github.jonaskahn.repositories

import com.google.inject.ImplementedBy
import io.github.jonaskahn.entities.Department
import io.github.jonaskahn.repositories.impl.DepartmentRepositoryImpl

@ImplementedBy(DepartmentRepositoryImpl::class)
interface DepartmentRepository {
    fun getAll(): List<Department>

    fun getById(id: Int): Department

    fun getByName(name: String): Department
}