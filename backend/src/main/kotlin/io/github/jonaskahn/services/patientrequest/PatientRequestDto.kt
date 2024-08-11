package io.github.jonaskahn.services.patientrequest

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.github.jonaskahn.assistant.jackson.MessageJsonSerializer
import io.github.jonaskahn.entities.enums.State
import io.github.jonaskahn.entities.enums.Status
import java.time.Instant
import java.time.LocalDate

open class PatientRequestDto {

    open var id: Int? = null
    open var numberOrder: Int? = null
    open var patientNumber: String? = null
    open var medicineCode: String? = null
    open var patientName: String? = null
    open var department: String? = null
    open var saveNumber: Int? = null
    open var inDate: LocalDate? = null
    open var outDate: LocalDate? = null
    open var receptionDate: Instant? = null
    open var appointmentPatientDate: LocalDate? = null
    open var copyQuantity: Int? = null
    open var copyCost: Int? = null
    open var note: String? = null
    open var donePatientDate: Instant? = null
    open var signDate: Instant? = null
    open var state: State? = State.PENDING
        set(value){
            field = value
            this.stateName = value?.name
        }

    @JsonSerialize(using = MessageJsonSerializer::class)
    open var stateName: String? = null

    open var status: Status? = Status.ACTIVATED
        set(value) {
            field = value
            this.statusName = value?.name
        }

    @JsonSerialize(using = MessageJsonSerializer::class)
    open var statusName: String? = null
}