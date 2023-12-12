package com.sdv.kit.omspace.data.auth

import com.sdv.kit.omspace.domain.auth.ConnectCodeManager
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64

class ConnectCodeManagerImpl : ConnectCodeManager {

    override fun getCodeVerifier(): String {
        val secureRandom = SecureRandom()
        val code = ByteArray(64)
        secureRandom.nextBytes(code)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(code)
    }

    override fun getCodeChallenge(verifier: String): String {
        val bytes = verifier.toByteArray()
        val messageDigest = MessageDigest.getInstance("SHA-256")
        messageDigest.update(bytes, 0, bytes.size)
        val digest = messageDigest.digest()
        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest)
    }
}