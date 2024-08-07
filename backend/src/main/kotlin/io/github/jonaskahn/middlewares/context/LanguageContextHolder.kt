package io.github.jonaskahn.middlewares.context

class LanguageContextHolder {
    companion object {
        private val threadLocalData: ThreadLocal<String?> = ThreadLocal()

        fun getLanguage(): String {
            return threadLocalData.get() ?: "en"
        }

        fun setLanguage(language: String?) {
            threadLocalData.set(language)
        }
    }

}