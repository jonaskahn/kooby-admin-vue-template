package io.github.jonaskahn.repositories

import com.google.inject.ImplementedBy
import io.github.jonaskahn.entities.PatientRequest
import io.github.jonaskahn.repositories.impl.PatientRequestRepositoryImpl
import io.github.jonaskahn.services.patientrequest.PatientRequestDto

@ImplementedBy(PatientRequestRepositoryImpl::class)
interface PatientRequestRepository {
    fun create(entity: PatientRequest)

    fun update(entity: PatientRequest)

    fun delete(id: Long)

    fun countByKeywordAndState(keyword: String?, state: Collection<Int>): Long

    fun searchByKeywordAndStateAndOffset(keyword: String?, state: Collection<Int>, offset: Long): Collection<PatientRequestDto>
}