package io.github.jonaskahn.assistant.query

import com.fasterxml.jackson.databind.JavaType
import io.github.jonaskahn.assistant.JsonMapper
import jakarta.persistence.Query
import org.hibernate.NonUniqueResultException
import org.hibernate.query.NativeQuery

class JpaQueryExecutor<T> private constructor() : QueryExecutor<T> {

    private var query: NativeQuery<Any>? = null

    private var target: Class<T>? = null

    class Executor<T> : QueryBuilder<T>, TransformBuilder<T> {

        private val executor = JpaQueryExecutor<T>()

        override fun with(query: Query, params: Map<String, Any>): TransformBuilder<T> {
            params.forEach(query::setParameter)
            executor.query = query.unwrap(NativeQuery::class.java) as NativeQuery<Any>
            return this
        }

        override fun with(query: Query): TransformBuilder<T> {
            executor.query = query.unwrap(NativeQuery::class.java) as NativeQuery<Any>
            return this
        }

        override fun map(clazz: Class<T>): JpaQueryExecutor<T> {
            executor.target = clazz
            return executor
        }

    }

    override fun list(): List<T> {
        val result = query!!.setTupleTransformer { tuple, aliases ->
            val result = HashMap<String, Any?>()
            for ((index, name) in aliases.withIndex()) {
                result[name] = tuple[index]
            }
            result
        }.list()
        return objectMapper.convertValue(
            result,
            getCollectionType(ArrayList::class.java, target!!)
        )
    }

    override fun unique(): T? {
        val result = list()
        val size = result.size
        return if (size == 0) {
            null
        } else {
            val first = result[0]
            for (i in 1 until size) {
                if (result[i] !== first) {
                    throw NonUniqueResultException(result.size)
                }
            }
            first
        }
    }

    override fun count(): Long {
        val result = query!!.setTupleTransformer { tuple, aliases ->
            val result = HashMap<String, Any?>()
            for ((index, name) in aliases.withIndex()) {
                result[name] = tuple[index]
            }
            result
        }.list()
        if (result.isEmpty()) {
            return 0
        }
        if (result.size > 1) {
            throw NonUniqueResultException(result.size)
        }
        return result[0].values.iterator().next() as Long
    }

    override fun first(): T? {
        val result = list()
        return if (result.isEmpty()) {
            null
        } else {
            list()[0]
        }
    }

    companion object {

        fun <T> builder() = Executor<T>()
        private val objectMapper = JsonMapper.instance

        private fun getCollectionType(collectionClass: Class<*>, vararg elementClasses: Class<*>): JavaType {
            return objectMapper.typeFactory
                .constructParametricType(collectionClass, *elementClasses)
        }
    }
}