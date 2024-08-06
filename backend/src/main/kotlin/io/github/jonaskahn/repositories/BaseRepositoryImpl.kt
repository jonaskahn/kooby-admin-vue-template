package io.github.jonaskahn.repositories

import io.github.jonaskahn.entities.BaseEntity
import io.jooby.Context
import jakarta.persistence.EntityManager
import org.pac4j.core.profile.UserProfile

open class BaseRepositoryImpl<Entity : BaseEntity, ID>(
    private val entityManager: EntityManager,
    private val entity: Class<Entity>,
    private val context: Context
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
        return context.getUser<UserProfile>()?.id?.toLong() ?: 0L
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

}