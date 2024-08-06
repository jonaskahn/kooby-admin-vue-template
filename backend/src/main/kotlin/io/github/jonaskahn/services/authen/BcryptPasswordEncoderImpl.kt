package io.github.jonaskahn.services.authen

import at.favre.lib.crypto.bcrypt.BCrypt

internal class BcryptPasswordEncoderImpl : PasswordEncoder {
    override fun encode(raw: String): String {
        return BCrypt.withDefaults().hashToString(12, raw.toCharArray())
    }

    override fun matches(raw: String, encoded: String): Boolean {
        return BCrypt.verifyer().verify(raw.toCharArray(), encoded.toCharArray()).verified
    }
}