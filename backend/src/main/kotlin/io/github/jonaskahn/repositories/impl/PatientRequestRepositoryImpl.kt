package io.github.jonaskahn.repositories.impl

import io.github.jonaskahn.controllers.patientrequest.PaginationResult
import io.github.jonaskahn.entities.PatientRequest
import io.github.jonaskahn.entities.enums.Status
import io.github.jonaskahn.repositories.AbstractBaseRepository
import io.github.jonaskahn.repositories.PatientRequestRepository
import io.github.jonaskahn.services.patientrequest.PatientRequestDto
import io.github.jonaskahn.services.patientrequest.PatientRequestEntityToDtoMapper
import io.jooby.Context
import jakarta.inject.Inject
import jakarta.persistence.EntityManager

class PatientRequestRepositoryImpl @Inject constructor(
    override val entityManager: EntityManager
): AbstractBaseRepository(entityManager) , PatientRequestRepository {
    override fun create(entity: PatientRequest) {
        entityManager.persist(entity)
    }

    override fun findByKeywordWithPagination(keyword: String, offset: Int, limit: Int): PaginationResult<PatientRequestDto> {
        val likeKeyword = "%${keyword.trim()}%"

        val queryStr = """
            SELECT pr FROM PatientRequest pr
            LEFT JOIN Delivery d ON d.idPatientRequest = pr
            WHERE (CAST(pr.numberOrder AS string) LIKE :keyword 
                OR pr.patientNumber LIKE :keyword 
                OR pr.patientName LIKE :keyword 
                OR pr.medicineCode LIKE :keyword)
                AND pr.status = :status
            ORDER BY pr.id DESC
        """
        val query = entityManager.createQuery(queryStr, PatientRequest::class.java)
        query.setParameter("keyword", likeKeyword)
        query.setParameter("status", Status.ACTIVATED)
        query.firstResult = offset
        query.maxResults = limit

        val data = query.resultList

        val countQueryStr = """
            SELECT COUNT(pr) FROM PatientRequest pr
            WHERE (CAST(pr.numberOrder AS string) LIKE :keyword 
                OR pr.patientNumber LIKE :keyword 
                OR pr.patientName LIKE :keyword 
                OR pr.medicineCode LIKE :keyword)
                AND pr.status = :status
        """
        val countQuery = entityManager.createQuery(countQueryStr, Long::class.javaObjectType)
        countQuery.setParameter("keyword", likeKeyword)
        countQuery.setParameter("status", Status.ACTIVATED)

        val totalItem = countQuery.singleResult
        val totalPage = (totalItem / limit + if (totalItem % limit == 0L) 0 else 1).toInt()
        val currentPage = (offset / limit) + 1
        val currentItem = data.size

        return PaginationResult(
            totalItem = totalItem,
            totalPage = totalPage,
            currentItem = currentItem,
            currentPage = currentPage,
            data = data.map { PatientRequestEntityToDtoMapper.INSTANCE.toDto(it) }
        )
    }


    override fun countByKeyword(keyword: String): Long {
        TODO("Not yet implemented")
    }
}