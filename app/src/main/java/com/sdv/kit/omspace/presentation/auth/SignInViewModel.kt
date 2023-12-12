package com.sdv.kit.omspace.presentation.auth

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdv.kit.omspace.domain.usecase.auth.google.LaunchGoogleIntentSender
import com.sdv.kit.omspace.domain.usecase.auth.google.SignInWithGoogle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val launchGoogleIntentSender: LaunchGoogleIntentSender,
    private val signInWithGoogle: SignInWithGoogle
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.LaunchIntentSender -> {
                launchIntentSender(event.launcher)
            }

            is SignInEvent.SignIn -> {
                signIn(event.intent!!)
            }
        }
    }

    private fun launchIntentSender(
        launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>
    ) {
        viewModelScope.launch {
            launchGoogleIntentSender(launcher = launcher)
        }
    }

    private fun signIn(intent: Intent) {
        viewModelScope.launch {
            val authResult = signInWithGoogle(intent = intent)

            _state.update { currentState ->
                currentState.copy(
                    authErrorMessage = authResult.errorMessage,
                    isSignInSuccessful = authResult.data != null
                )
            }
        }
    }
}