package io.github.jonaskahn.entities

import io.github.jonaskahn.entities.converter.StatusConverter
import io.github.jonaskahn.entities.enums.Status
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.time.Instant
import java.time.LocalDate

@Entity
@Table(name = "assignments")
open class Assignment: BaseEntity() {
    @Id
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient_request")
    open var idPatientRequest: io.github.jonaskahn.entities.PatientRequest? = null

    @Column(name = "id_copy_user")
    open var idCopyUser: Int? = null

    @Column(name = "appointment_date")
    open var appointmentDate: LocalDate? = null

    @Column(name = "completion_date")
    open var completionDate: Instant? = null

    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    @Convert(converter = StatusConverter::class)
    open var status: Status? = Status.ACTIVATED
}