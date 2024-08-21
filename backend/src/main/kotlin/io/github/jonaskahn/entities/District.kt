package io.github.jonaskahn.entities

import io.github.jonaskahn.entities.converter.StatusConverter
import io.github.jonaskahn.entities.enums.Status
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "district")
open class District: BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @JoinColumn(name = "id_province")
    open var idProvince: Int? = null

    @Size(max = 255)
    @Column(name = "name")
    open var name: String? = null

    @Column(name = "delivery_fee")
    open var deliveryFee: Int? = null

    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    @Convert(converter = StatusConverter::class)
    open var status: Status? = Status.ACTIVATED
}