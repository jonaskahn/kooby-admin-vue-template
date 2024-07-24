package io.github.jonaskahn.assistant

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper

object JsonMapper {
    val instance: JsonMapper = JsonMapper.builder()
        .findAndAddModules()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
        .build()
}