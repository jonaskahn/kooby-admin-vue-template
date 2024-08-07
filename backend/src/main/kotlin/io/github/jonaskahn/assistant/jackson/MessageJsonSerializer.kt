package io.github.jonaskahn.assistant.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import io.github.jonaskahn.assistant.DataUtils
import io.github.jonaskahn.assistant.Language
import org.slf4j.LoggerFactory

class MessageJsonSerializer : JsonSerializer<String>() {
    override fun serialize(value: String?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        try {
            gen?.writeObject(value?.let { Language.of(it) })
        } catch (ignored: Exception) {
            log.warn("Cannot translate message: [ $value ]", ignored)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(DataUtils::class.java)

    }
}
