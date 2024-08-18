package io.github.jonaskahn.entities

import io.github.jonaskahn.entities.converter.StatusConverter
import io.github.jonaskahn.entities.enums.Status
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "department")
open class Department: BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @Size(max = 10)
    @Column(name = "name", length = 10)
    open var name: String? = null

    @Size(max = 255)
    @Column(name = "full_name")
    open var fullName: String? = null

    @Size(max = 50)
    @Column(name = "manager", length = 50)
    open var manager: String? = null

    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    @Convert(converter = StatusConverter::class)
    open var status: Status? = Status.ACTIVATED
}