package io.github.jonaskahn.entities.converter

import io.github.jonaskahn.assistant.DataUtils
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class StringCollectionConverter : AttributeConverter<List<String>, String> {
    override fun convertToDatabaseColumn(lst: List<String>?): String? {
        return DataUtils.convertJsonToString(lst)
    }

    override fun convertToEntityAttribute(s: String?): List<String>? {
        return DataUtils.convertJsonStringCollection(s)
    }
}
