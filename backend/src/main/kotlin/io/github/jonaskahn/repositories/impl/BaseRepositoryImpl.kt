package io.github.jonaskahn.repositories.impl

import io.github.jonaskahn.assistant.query.JpaQueryExecutor
import io.github.jonaskahn.entities.BaseEntity
import io.github.jonaskahn.middlewares.context.UserContextHolder
import io.github.jonaskahn.repositories.BaseRepository
import jakarta.persistence.EntityManager

abstract class BaseRepositoryImpl<Entity : BaseEntity, ID>(
    open val entityManager: EntityManager,
    private val entity: Class<Entity>
) : BaseRepository<Entity, ID> {

    override fun create(e: Entity) {
        e.createdBy = getCurrentLoggedUserId()
        e.updatedBy = getCurrentLoggedUserId()
        entityManager.persist(e)
    }

    override fun update(e: Entity) {
        e.updatedBy = getCurrentLoggedUserId()
        entityManager.persist(e)
    }

    private fun getCurrentLoggedUserId(): Long {
        return UserContextHolder.getCurrentUserId()
    }

    override fun delete(e: Entity) {
        entityManager.remove(e)
    }

    override fun deleteById(id: ID) {
        findById(id)?.let { entityManager.remove(it) }
    }

    override fun findById(id: ID): Entity? {
        return entityManager.find(entity, id)
    }

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
}