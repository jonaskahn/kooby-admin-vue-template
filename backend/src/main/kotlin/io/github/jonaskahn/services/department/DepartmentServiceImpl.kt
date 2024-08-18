package io.github.jonaskahn.services.department

import io.github.jonaskahn.entities.Department
import io.github.jonaskahn.repositories.DepartmentRepository
import jakarta.inject.Inject

class DepartmentServiceImpl @Inject constructor(
    private val departmentRepository: DepartmentRepository
) : DepartmentService {
    override fun getDepartmentById(id: Int): Department {
        return departmentRepository.getById(id)
    }

    override fun getDepartments(): List<Department> {
        return departmentRepository.getAll()
    }

    override fun getDepartmentsByName(name: String): Department {
        return departmentRepository.getByName(name)
    }
}