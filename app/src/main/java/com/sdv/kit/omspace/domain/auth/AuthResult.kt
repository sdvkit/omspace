package com.sdv.kit.omspace.domain.auth

import com.sdv.kit.omspace.domain.model.UserData

data class AuthResult(
    val data: UserData?,
    val errorMessage: String?
)