package io.github.jonaskahn.repositories

import com.google.inject.ImplementedBy
import io.github.jonaskahn.controllers.patientrequest.PaginationResult
import io.github.jonaskahn.entities.PatientRequest
import io.github.jonaskahn.repositories.impl.PatientRequestRepositoryImpl
import io.github.jonaskahn.services.patientrequest.PatientRequestDto

@ImplementedBy(PatientRequestRepositoryImpl::class)
interface PatientRequestRepository: BaseRepository<PatientRequest, Long> {

    fun findByKeywordWithPagination(keyword: String, offset: Int, limit: Int): PaginationResult<PatientRequestDto>

    fun countByKeyword(keyword: String): Long
}