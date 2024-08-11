package io.github.jonaskahn.services.address

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.github.jonaskahn.assistant.jackson.MessageJsonSerializer
import io.github.jonaskahn.entities.enums.Status
open class ProvinceDto {

    open var id: Int? = null

    open var name: String? = null

    open var status: Status? = Status.ACTIVATED
        set(value) {
            field = value
            this.statusName = value?.name
        }

    @JsonSerialize(using = MessageJsonSerializer::class)
    open var statusName: String? = null
}