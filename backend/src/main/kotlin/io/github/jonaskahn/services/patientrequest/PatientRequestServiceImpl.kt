package io.github.jonaskahn.services.patientrequest

import io.github.jonaskahn.assistant.PageData
import io.github.jonaskahn.controllers.patientrequest.PatientRequestForm
import io.github.jonaskahn.entities.PatientRequest
import io.github.jonaskahn.entities.enums.State
import io.github.jonaskahn.repositories.PatientRequestRepository
import io.github.jonaskahn.services.PagingService
import jakarta.inject.Inject

internal class PatientRequestServiceImpl @Inject constructor(
    private val patientRequestRepository: PatientRequestRepository,
): PatientRequestService, PagingService() {
    override fun createRequest(request: PatientRequestForm) {
        val newRequest = PatientRequest().apply {
            numberOrder = request.numberOrder
            patientNumber = request.patientNumber
            medicineCode = request.medicineCode
            patientName = request.patientName
            department = request.department
            saveNumber = request.saveNumber
            inDate = request.inDate
            outDate = request.outDate
            receptionDate = request.receptionDate
            appointmentPatientDate = request.appointmentPatientDate
            copyQuantity = request.copyQuantity
            copyCost = request.copyCost
            note = request.note
            isDelivery = request.isDelivery
            deliveryOrderNumber = request.deliveryOrderNumber
            deliveryYearOfOrder = request.deliveryYearOfOrder
            deliveryAddress = request.deliveryAddress
            deliveryPhone = request.deliveryPhone
            idProvince = request.idProvince
            idDistrict = request.idDistrict
            deliveryCost = request.deliveryCost
        }

        patientRequestRepository.create(newRequest)
    }


    override fun updateRequest(request: PatientRequestForm) {
        val existingRequest = PatientRequest().apply  {
                id = request.id
                numberOrder = request.numberOrder ?: numberOrder
                patientNumber = request.patientNumber ?: patientNumber
                medicineCode = request.medicineCode ?: medicineCode
                patientName = request.patientName ?: patientName
                department = request.department ?: department
                saveNumber = request.saveNumber ?: saveNumber
                inDate = request.inDate ?: inDate
                outDate = request.outDate ?: outDate
                receptionDate = request.receptionDate ?: receptionDate
                appointmentPatientDate = request.appointmentPatientDate ?: appointmentPatientDate
                copyQuantity = request.copyQuantity ?: copyQuantity
                copyCost = request.copyCost ?: copyCost
                note = request.note ?: note
                isDelivery = request.isDelivery ?: isDelivery
                deliveryOrderNumber = request.deliveryOrderNumber ?: deliveryOrderNumber
                deliveryYearOfOrder = request.deliveryYearOfOrder ?: deliveryYearOfOrder
                deliveryAddress = request.deliveryAddress ?: deliveryAddress
                deliveryPhone = request.deliveryPhone ?: deliveryPhone
                idProvince = request.idProvince ?: idProvince
                idDistrict = request.idDistrict ?: idDistrict
                deliveryCost = request.deliveryCost ?: deliveryCost
            }
        patientRequestRepository.update(existingRequest)
    }


    override fun deleteRequest(requestId: Int) {
        TODO("Not yet implemented")
    }

    override fun search(
        keyword: String?,
        states: Collection<State>,
        pageNo: Long
    ): PageData<PatientRequestDto> {
        return super.search(
            listOf(),
            states,
            pageNo,
            {_, state -> patientRequestRepository.countByKeywordAndState(keyword, state)},
            {_, state, offset -> patientRequestRepository.searchByKeywordAndStateAndOffset(keyword, state, offset)}

        )
    }
}