package io.github.jonaskahn.services.user

import com.fasterxml.jackson.annotation.JsonAlias
import io.github.jonaskahn.entities.enums.Status
import java.io.Serializable
import java.time.Instant

open class UserInfoDto : Serializable {
    open var id: Long? = null
    open var username: String? = null
    open var email: String? = null

    @JsonAlias("full_name")
    open var fullName: String? = null
    open var status: Status? = null
        set(value) {
            field = value
            this.statusName = value?.description
        }
    open var statusName: String? = null

    @JsonAlias("created_at")
    open var createdAt: Instant? = null

    @JsonAlias("created_by")
    open var createdBy: Long? = null

    @JsonAlias("updated_at")
    open var updatedAt: Instant? = null

    @JsonAlias("updated_by")
    open var updatedBy: Long? = null
}