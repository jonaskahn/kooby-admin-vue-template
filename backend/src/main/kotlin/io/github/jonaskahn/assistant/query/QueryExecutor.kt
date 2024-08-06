package io.github.jonaskahn.assistant.query

interface QueryExecutor<T> {

    fun count(): Long

    fun list(): List<T>

    fun unique(): T?

    fun first(): T?

    fun getListResult(): List<T> {
        return list()
    }

    fun getSingleResult(): T? {
        return unique()
    }

    fun getFirstResult(): T? {
        return first()
    }
}