package io.github.jonaskahn.services.address

import io.github.jonaskahn.entities.enums.Status

open class DistrictDto {
    open var id: Int? = null

    open var idProvince: Int? = null

    open var name: String? = null

    open var status: Status? = Status.ACTIVATED
        set(value) {
            field = value
            this.statusName = value?.name
        }
    open var statusName: String? = null
}