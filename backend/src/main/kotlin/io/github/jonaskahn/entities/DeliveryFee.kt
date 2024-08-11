package io.github.jonaskahn.entities

import io.github.jonaskahn.entities.converter.StatusConverter
import io.github.jonaskahn.entities.enums.Status
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "delivery_fee")
open class DeliveryFee: BaseEntity() {
    @Id
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_district")
    open var idDistrict: io.github.jonaskahn.entities.District? = null

    @Column(name = "fee")
    open var fee: Int? = null

    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    @Convert(converter = StatusConverter::class)
    open var status: Status? = Status.ACTIVATED
}