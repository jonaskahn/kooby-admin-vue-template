package io.github.jonaskahn.repositories.impl

import io.github.jonaskahn.constants.Defaults
import io.github.jonaskahn.entities.Assignment
import io.github.jonaskahn.entities.PatientRequest
import io.github.jonaskahn.entities.enums.Status
import io.github.jonaskahn.repositories.AbstractBaseRepository
import io.github.jonaskahn.repositories.PatientRequestRepository
import io.github.jonaskahn.services.patientrequest.PatientRequestDto
import io.github.jonaskahn.services.patientrequest.PatientRequestEntityToDtoMapper
import jakarta.inject.Inject
import jakarta.persistence.EntityManager

class PatientRequestRepositoryImpl @Inject constructor(
    override val entityManager: EntityManager
): AbstractBaseRepository(entityManager) , PatientRequestRepository {
    override fun create(entity: PatientRequest) {
        entityManager.persist(entity)
    }

    override fun countByKeywordAndState(keyword: String?, state: Collection<Int>): Long {
        val likeKeyword = "%${keyword?.trim()}%"
        val countQueryStr = """
            SELECT COUNT(pr) FROM PatientRequest pr
            WHERE (CAST(pr.numberOrder AS string) LIKE :keyword 
                OR pr.patientNumber LIKE :keyword 
                OR pr.patientName LIKE :keyword 
                OR pr.medicineCode LIKE :keyword)
                AND pr.state in :states
                AND pr.status = :status
        """
        val countQuery = entityManager.createQuery(countQueryStr, Long::class.javaObjectType)
        countQuery.setParameter("keyword", likeKeyword)
        countQuery.setParameter("states", state)
        countQuery.setParameter("status", Status.ACTIVATED)
        return countQuery.singleResult
    }

    override fun searchByKeywordAndStateAndOffset(
        keyword: String?,
        state: Collection<Int>,
        offset: Long
    ): Collection<PatientRequestDto> {
        val likeKeyword = "${keyword?.trim()}%"

        val queryStr = """
            SELECT pr, a FROM PatientRequest pr
            LEFT JOIN Assignment a ON a.idPatientRequest =pr
            WHERE (CAST(pr.numberOrder AS string) LIKE :keyword 
                OR pr.patientNumber LIKE :keyword 
                OR pr.patientName LIKE :keyword 
                OR pr.medicineCode LIKE :keyword)
                AND pr.state in :states
                AND pr.status = :status
            ORDER BY pr.id DESC
        """
        val query = entityManager.createQuery(queryStr, Array<Any>::class.java)
        query.setParameter("keyword", likeKeyword)
        query.setParameter("states", state)
        query.setParameter("status", Status.ACTIVATED)
        query.firstResult = offset.toInt()
        query.maxResults = Defaults.Pageable.DEFAULT_PAGE_SIZE

        val results = query.resultList

        return results.map { result ->
            val patientRequest = result[0] as PatientRequest
            val assignment = result[1] as? Assignment
            PatientRequestEntityToDtoMapper.INSTANCE.toDto(patientRequest, assignment)
        }
    }
}