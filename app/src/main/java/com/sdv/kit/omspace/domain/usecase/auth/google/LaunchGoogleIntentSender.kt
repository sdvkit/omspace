package com.sdv.kit.omspace.domain.usecase.auth.google

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import com.sdv.kit.omspace.data.auth.GoogleAuthManager
import javax.inject.Inject

class LaunchGoogleIntentSender @Inject constructor(
    private val googleAuthManager: GoogleAuthManager
) {

    suspend operator fun invoke(
        launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>
    ) {
        val intentSenderRequest = googleAuthManager.buildIntentSenderRequest() ?: return
        launcher.launch(intentSenderRequest)
    }
}