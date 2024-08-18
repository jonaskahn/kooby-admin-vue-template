package io.github.jonaskahn.controllers.department

import io.github.jonaskahn.entities.Department
import io.github.jonaskahn.services.department.DepartmentService
import io.jooby.annotation.GET
import io.jooby.annotation.Path
import io.jooby.annotation.PathParam
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.inject.Inject

@Tag(name = "Department", description = "APIs for department management")
@Path("/secure")
class DepartmentController @Inject constructor(private val departmentService: DepartmentService) {

    @GET("/department/{id}")
    fun getDepartmentById(@PathParam("id") id: Int): Department {
        return departmentService.getDepartmentById(id)
    }

    @GET("/departments")
    fun getAllDepartments(): List<Department> {
        return departmentService.getDepartments()
    }

    @GET("/department/name/{name}")
    fun getDepartmentByName(@PathParam("name") name: String): Department {
        return departmentService.getDepartmentsByName(name)
    }
}