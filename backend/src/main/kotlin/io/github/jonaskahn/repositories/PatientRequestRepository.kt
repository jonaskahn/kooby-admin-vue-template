package io.github.jonaskahn.repositories

import com.google.inject.ImplementedBy
import io.github.jonaskahn.controllers.patientrequest.PaginationResult
import io.github.jonaskahn.entities.PatientRequest
import io.github.jonaskahn.repositories.impl.PatientRequestRepositoryImpl
import io.github.jonaskahn.services.patientrequest.PatientRequestDto

@ImplementedBy(PatientRequestRepositoryImpl::class)
interface PatientRequestRepository {
    fun create(entity: PatientRequest)

    fun findByKeywordWithPagination(keyword: String, offset: Int, limit: Int): PaginationResult<PatientRequestDto>

    fun countByKeyword(keyword: String): Long

    fun searchByKeywordAndStateAndOffset(keyword: String, state: Collection<Int>, offset: Int): Collection<PatientRequestDto>
}