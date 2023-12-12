package com.sdv.kit.omspace.presentation.auth

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest

sealed class SignInEvent {

    class LaunchIntentSender(
        val launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>
    ) : SignInEvent()

    class SignIn(val intent: Intent?) : SignInEvent()
}