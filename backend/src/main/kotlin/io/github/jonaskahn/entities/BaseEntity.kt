package io.github.jonaskahn.entities

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.time.Instant

@MappedSuperclass
open class BaseEntity {

    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    open var createdAt: Instant? = null

    @Column(name = "created_by")
    open var createdBy: Long? = null

    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    open var updatedAt: Instant? = null

    @Column(name = "updated_by")
    open var updatedBy: Long? = null

    @PrePersist
    fun onPrePersist() {
        this.createdAt = Instant.now()
        this.updatedAt = Instant.now()
    }

    @PreUpdate
    fun onPreUpdate() {
        this.updatedAt = Instant.now()
    }
}