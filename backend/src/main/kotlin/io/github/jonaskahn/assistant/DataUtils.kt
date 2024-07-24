package io.github.jonaskahn.assistant

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import org.apache.commons.lang3.ObjectUtils
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory


object DataUtils {

    private val log = LoggerFactory.getLogger(DataUtils::class.java)

    private val objectMapper = JsonMapper.builder()
        .findAndAddModules()
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
        .build()


    fun convertJsonStringCollection(data: String?): List<String>? {
        if (StringUtils.isEmpty(data)) {
            return listOf()
        }
        return try {
            objectMapper.readValue(data, object : TypeReference<List<String>>() {})
        } catch (e: Exception) {
            log.warn("Cannot convert json string to list", e)
            listOf()
        }
    }

    fun <T> convertJsonToString(data: T?): String? {
        if (ObjectUtils.isEmpty(data)) {
            return null
        }
        return try {
            objectMapper.writeValueAsString(data)
        } catch (e: Exception) {
            log.warn("Cannot convert data to json string", e)
            StringUtils.EMPTY
        }
    }
}
