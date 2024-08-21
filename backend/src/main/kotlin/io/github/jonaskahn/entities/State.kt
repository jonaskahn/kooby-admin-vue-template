package io.github.jonaskahn.entities

import io.github.jonaskahn.entities.converter.StatusConverter
import io.github.jonaskahn.entities.enums.Status
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@Entity
@Table(name = "states")
open class State: BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @ColumnDefault("0")
    @Column(name = "code")
    open var code: Byte? = null

    @Size(max = 40)
    @Column(name = "name", length = 40)
    open var name: String? = null

    @Size(max = 255)
    @Column(name = "descriptions")
    open var descriptions: String? = null

    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    @Convert(converter = StatusConverter::class)
    open var status: Status? = Status.ACTIVATED
}