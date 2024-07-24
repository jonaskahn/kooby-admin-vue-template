package io.github.jonaskahn.assistant

import org.slf4j.LoggerFactory
import java.text.MessageFormat
import java.util.*

class Language {
    companion object {
        private val log = LoggerFactory.getLogger(Language::class.java)
        private val englishResourceBundle = ResourceBundle.getBundle("messages", Locale.ENGLISH)
        private val vietnameseResourceBundle = ResourceBundle.getBundle("messages", Locale.of("vi"))

        private val resourceBundles: Map<String, ResourceBundle> = mapOf(
            "en" to englishResourceBundle,
            "en_US" to englishResourceBundle,
            "vi" to vietnameseResourceBundle,
            "vi_VN" to vietnameseResourceBundle
        )

        fun of(language: String?, key: String?, vararg variables: Any?): String? {
            try {
                if (key == null) return null
                val bundle: ResourceBundle? = resourceBundles[language] ?: resourceBundles["en"]
                val value = bundle?.getString(key) ?: key
                return MessageFormat.format(value, *variables)
            } catch (ignored: Exception) {
                log.warn("Key '$key' cannot parse to language: [${language ?: "en"}]")
                return key
            }
        }
    }
}