package io.github.jonaskahn.repositories

import io.github.jonaskahn.assistant.query.JpaQueryExecutor
import jakarta.persistence.EntityManager
import org.slf4j.LoggerFactory

abstract class AbstractBaseRepository(
    open val entityManager: EntityManager
) {

    protected fun <T> search(
        useNativeQuery: Boolean,
        initSql: String,
        clazz: Class<T>,
        conditionalBuilder: (sqlBuilder: StringBuilder, params: MutableMap<String, Any>) -> Unit
    ): Collection<T> {
        return if (useNativeQuery) {
            internalNativeSearch(initSql, clazz, conditionalBuilder)
        } else {
            internalSearch(initSql, clazz, conditionalBuilder)
        }
    }


    private fun <T> internalSearch(
        initSql: String,
        clazz: Class<T>,
        conditionalBuilder: (sqlBuilder: StringBuilder, params: MutableMap<String, Any>) -> Unit
    ): Collection<T> {
        val sql = StringBuilder(initSql)
        val params = mutableMapOf<String, Any>()
        conditionalBuilder(sql, params)

        val query = entityManager.createQuery(sql.toString(), clazz)
        params.forEach { (k, v) -> query.setParameter(k, v) }

        return query.resultList
    }


    private fun <T> internalNativeSearch(
        initSql: String,
        clazz: Class<T>,
        conditionalBuilder: (sqlBuilder: StringBuilder, params: MutableMap<String, Any>) -> Unit
    ): Collection<T> {
        val sql = StringBuilder(initSql)
        val params = mutableMapOf<String, Any>()
        conditionalBuilder(sql, params)

        val query = entityManager.createNativeQuery(sql.toString())
        return JpaQueryExecutor.builder<T>()
            .with(query, params)
            .map(clazz)
            .list()
    }

    protected fun count(
        useNativeQuery: Boolean,
        initSql: String,
        conditionalBuilder: (sqlBuilder: StringBuilder, params: MutableMap<String, Any>) -> Unit
    ): Long {
        return if (useNativeQuery) {
            privateNativeCount(initSql, conditionalBuilder)
        } else {
            privateCount(initSql, conditionalBuilder)
        }
    }

    private fun privateCount(
        initSql: String,
        conditionalBuilder: (sqlBuilder: StringBuilder, params: MutableMap<String, Any>) -> Unit
    ): Long {
        val sql = StringBuilder(initSql)
        val params = mutableMapOf<String, Any>()
        conditionalBuilder(sql, params)

        val query = entityManager.createQuery(sql.toString(), Long::class.java)
        params.forEach { (k, v) -> query.setParameter(k, v) }

        return query.singleResult
    }

    private fun privateNativeCount(
        initSql: String,
        conditionalBuilder: (sqlBuilder: StringBuilder, params: MutableMap<String, Any>) -> Unit
    ): Long {
        val sql = StringBuilder(initSql)
        val params = mutableMapOf<String, Any>()
        conditionalBuilder(sql, params)

        val query = entityManager.createNativeQuery(sql.toString())

        return JpaQueryExecutor.builder<Long>()
            .with(query, params)
            .map(Long::class.java)
            .count()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}