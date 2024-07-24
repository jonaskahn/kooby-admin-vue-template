package io.github.jonaskahn.assistant.query

interface TransformBuilder<T> {
    fun map(clazz: Class<T>): QueryExecutor<T>
}