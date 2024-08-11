package io.github.jonaskahn.entities

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "state_history")
open class StateHistory: BaseEntity() {
    @Id
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient_request")
    open var idPatientRequest: PatientRequest? = null

    @Column(name = "state")
    open var state: Byte? = null
}