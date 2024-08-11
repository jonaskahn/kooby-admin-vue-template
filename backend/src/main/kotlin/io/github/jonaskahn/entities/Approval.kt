package io.github.jonaskahn.entities

import io.github.jonaskahn.entities.converter.StatusConverter
import io.github.jonaskahn.entities.enums.Status
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "approvals")
open class Approval: BaseEntity() {
    @Id
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_patient_request", nullable = false)
    open var idPatientRequest: io.github.jonaskahn.entities.PatientRequest? = null

    @NotNull
    @Column(name = "id_user", nullable = false)
    open var idUser: Int? = null

    @Size(max = 255)
    @Column(name = "role")
    open var role: String? = null

    @Column(name = "approval_date")
    open var approvalDate: Instant? = null

    @ColumnDefault("0")
    @Column(name = "status", nullable = false)
    @Convert(converter = StatusConverter::class)
    open var status: Status? = Status.INACTIVATED

    @Size(max = 4000)
    @Column(name = "comment", length = 4000)
    open var comment: String? = null
}