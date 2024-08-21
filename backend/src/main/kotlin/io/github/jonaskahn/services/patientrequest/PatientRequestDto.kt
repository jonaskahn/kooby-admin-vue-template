package io.github.jonaskahn.services.patientrequest

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.github.jonaskahn.assistant.jackson.MessageJsonSerializer
import io.github.jonaskahn.entities.enums.State
import io.github.jonaskahn.entities.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Lob
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant
import java.time.LocalDate

open class PatientRequestDto {

    open var idPatientRequest: Int? = null
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
    open var state: State? = null
        set(value) {
            field = value
            this.stateName = value?.description
        }

    @JsonSerialize(using = MessageJsonSerializer::class)
    open var stateName: String? = null

    open var requestStatus: Status? = null
        set(value) {
            field = value
            this.statusName = value?.description
        }

    @JsonSerialize(using = MessageJsonSerializer::class)
    open var statusName: String? = null
    open var isDelivery: Int? = null
    open var deliveryOrderNumber: Int? = null
    open var deliveryYearOfOrder: Int? = null
    open var deliveryAddress: String? = null
    open var deliveryPhone: String? = null
    open var idDistrict: Int? = null
    open var deliveryCost: Int? = null
    open var deliveryDate: Instant? = null

    // assignment table
    open var idAssignment: Int? = null
    open var idCopyUser: Int? = null
    open var appointmentDate: LocalDate? = null
    open var completionDate: Instant? = null
}