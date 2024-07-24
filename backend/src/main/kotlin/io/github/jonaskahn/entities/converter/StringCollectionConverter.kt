package io.github.jonaskahn.entities.converter

import io.github.jonaskahn.assistant.DataUtils
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class StringCollectionConverter : AttributeConverter<List<String>, String> {
    override fun convertToDatabaseColumn(m: List<String>?): String? {
        return DataUtils.convertJsonToString(m)
    }

    override fun convertToEntityAttribute(s: String?): List<String>? {
        return DataUtils.convertJsonStringCollection(s)
    }
}
