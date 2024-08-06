package io.github.jonaskahn.assistant.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.apache.commons.lang3.StringUtils
import java.io.IOException
import java.util.*

class StringToStringCollectionDeserializer : JsonDeserializer<List<String>>() {
    @Throws(IOException::class)
    override fun deserialize(p: JsonParser, ctx: DeserializationContext): List<String> {
        val value = Optional.ofNullable(p.valueAsString)
            .orElse(StringUtils.EMPTY)
        return value.split(",".toRegex())
            .asSequence()
            .map { it.replace("[", "").replace("]", "") }
            .map { it.trim() }
            .map { it.substring(1) }
            .map { it.trim() }
            .map { StringUtils.chop(it) }
            .toList()
    }
}
