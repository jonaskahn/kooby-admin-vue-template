package io.github.jonaskahn.entities.converter

import io.github.jonaskahn.entities.enums.State
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class StateConverter : AttributeConverter<State, Int> {
    override fun convertToDatabaseColumn(state: State?): Int? {
        return state?.id
    }

    override fun convertToEntityAttribute(id: Int?): State {
        return State.of(id ?: 0)
    }
}