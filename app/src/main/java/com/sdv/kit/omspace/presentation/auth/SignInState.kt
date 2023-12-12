package com.sdv.kit.omspace.presentation.auth

data class SignInState(
    val authErrorMessage: String? = null,
    val isSignInSuccessful: Boolean = false
)