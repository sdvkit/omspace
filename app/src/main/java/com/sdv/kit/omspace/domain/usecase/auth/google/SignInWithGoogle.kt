package com.sdv.kit.omspace.domain.usecase.auth.google

import android.content.Intent
import com.sdv.kit.omspace.domain.auth.AuthResult
import com.sdv.kit.omspace.data.auth.GoogleAuthManager
import javax.inject.Inject

class SignInWithGoogle @Inject constructor(
    private val googleAuthManager: GoogleAuthManager
) {

    suspend operator fun invoke(intent: Intent): AuthResult =
        googleAuthManager.signIn(intent)
}