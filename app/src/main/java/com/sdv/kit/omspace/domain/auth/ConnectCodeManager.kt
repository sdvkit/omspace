package com.sdv.kit.omspace.domain.auth

interface ConnectCodeManager {
    fun getCodeVerifier(): String
    fun getCodeChallenge(verifier: String): String
}