package io.github.jonaskahn.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant
import java.time.LocalDate

@Entity
@Table(name = "deliveries")
open class Delivery: BaseEntity() {
    @Id
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_patient_request", nullable = false)
    open var idPatientRequest: io.github.jonaskahn.entities.PatientRequest? = null

    @Column(name = "order_number")
    open var orderNumber: Int? = null

    @Column(name = "year_of_order")
    open var yearOfOrder: Int? = null

    @Size(max = 4000)
    @Column(name = "address", length = 4000)
    open var address: String? = null

    @Size(max = 15)
    @Column(name = "phone", length = 15)
    open var phone: String? = null

    @Column(name = "id_province")
    open var idProvince: Int? = null

    @Column(name = "id_district")
    open var idDistrict: Int? = null

    @Column(name = "cost")
    open var cost: Int? = null

    @Column(name = "delivery_date")
    open var deliveryDate: LocalDate? = null

}