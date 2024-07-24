package io.github.jonaskahn.repositories

import io.github.jonaskahn.entities.BaseEntity
import io.jooby.Context
import jakarta.persistence.EntityManager
import org.pac4j.core.profile.UserProfile

open class BaseRepository<Entity : BaseEntity, ID>(
    private val context: Context,
    private val em: EntityManager
) {

    fun create(user: Entity) {
        user.createdBy = getCurrentLoggedUserId()
        user.updatedBy = getCurrentLoggedUserId()
        em.persist(user)
    }

    fun update(user: Entity) {
        user.updatedBy = getCurrentLoggedUserId()
        em.persist(user)
    }

    private fun getCurrentLoggedUserId(): Long {
        return context.getUser<UserProfile>()?.id?.toLong() ?: 0L
    }

}