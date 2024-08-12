package io.github.jonaskahn.middlewares.context

import org.pac4j.core.profile.UserProfile

class UserContextHolder {
    companion object {
        private val threadLocalData: ThreadLocal<UserProfile?> = ThreadLocal()

        fun getCurrentUser(): UserProfile? {
            return threadLocalData.get()
        }

        fun getCurrentUserId(): Long {
            return threadLocalData.get()?.id?.toLong() ?: 0L
        }

        fun getCurrentUserRoles(): Set<String>? {
            return threadLocalData.get()?.roles
        }

        fun setCurrentUserInfo(userInfo: UserProfile?) {
            threadLocalData.set(userInfo)
        }
    }
}