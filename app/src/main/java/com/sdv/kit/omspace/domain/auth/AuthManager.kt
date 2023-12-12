package com.sdv.kit.omspace.domain.auth

import android.content.Intent
import com.sdv.kit.omspace.domain.model.UserData

interface AuthManager {
    suspend fun signIn(intent: Intent? = null): AuthResult
    suspend fun signOut()
    fun getSignedInUser(): UserData?
}