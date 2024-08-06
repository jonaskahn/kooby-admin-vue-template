package io.github.jonaskahn.services.authen

import com.google.inject.ImplementedBy

@ImplementedBy(BcryptPasswordEncoderImpl::class)
interface PasswordEncoder {
    fun encode(raw: String): String

    fun matches(raw: String, encoded: String): Boolean
}