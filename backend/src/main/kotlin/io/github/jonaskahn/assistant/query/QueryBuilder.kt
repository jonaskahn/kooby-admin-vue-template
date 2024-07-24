package io.github.jonaskahn.assistant.query

import jakarta.persistence.Query


interface QueryBuilder<T> {
    fun with(query: Query, params: Map<String, Any>): TransformBuilder<T>
    fun with(query: Query): TransformBuilder<T>
}