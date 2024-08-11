package io.github.jonaskahn.entities

import io.github.jonaskahn.entities.converter.StateConverter
import io.github.jonaskahn.entities.converter.StatusConverter
import io.github.jonaskahn.entities.enums.State
import io.github.jonaskahn.entities.enums.Status
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant
import java.time.LocalDate

@Entity
@Table(name = "patient_request")
open class PatientRequest: BaseEntity() {
    @Id
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Column(name = "number_order")
    open var numberOrder: Int? = null

    @Size(max = 10)
    @Column(name = "patient_number", length = 10)
    open var patientNumber: String? = null

    @Size(max = 15)
    @Column(name = "medicine_code", length = 15)
    open var medicineCode: String? = null

    @Size(max = 255)
    @Column(name = "patient_name")
    open var patientName: String? = null

    @Size(max = 10)
    @Column(name = "department", length = 10)
    open var department: String? = null

    @ColumnDefault("0")
    @Column(name = "save_number")
    open var saveNumber: Int? = null

    @Column(name = "in_date")
    open var inDate: LocalDate? = null

    @Column(name = "out_date")
    open var outDate: LocalDate? = null

    @Column(name = "reception_date")
    open var receptionDate: Instant? = null

    @Column(name = "appointment_patient_date")
    open var appointmentPatientDate: LocalDate? = null

    @ColumnDefault("1")
    @Column(name = "copy_quantity")
    open var copyQuantity: Int? = null

    @Column(name = "copy_cost")
    open var copyCost: Int? = null

    @Lob
    @Column(name = "note")
    open var note: String? = null

    @Column(name = "done_patient_date")
    open var donePatientDate: Instant? = null

    @Column(name = "sign_date")
    open var signDate: Instant? = null

    @ColumnDefault("0")
    @Column(name = "state")
    @Convert(converter = StateConverter::class)
    open var state: State? = State.PENDING

    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    @Convert(converter = StatusConverter::class)
    open var status: Status? = Status.ACTIVATED
}