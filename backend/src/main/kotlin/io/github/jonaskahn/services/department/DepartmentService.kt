package io.github.jonaskahn.services.department

import com.google.inject.ImplementedBy
import io.github.jonaskahn.entities.Department

@ImplementedBy(DepartmentServiceImpl::class)
interface DepartmentService {
    fun getDepartmentById(id: Int): Department

    fun getDepartments(): List<Department>

    fun getDepartmentsByName(name: String): Department
}