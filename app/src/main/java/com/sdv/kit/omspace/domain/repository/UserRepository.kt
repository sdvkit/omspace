package com.sdv.kit.omspace.domain.repository

import com.sdv.kit.omspace.domain.model.UserData

interface UserRepository {
    suspend fun getGoogleSignedInUser(): UserData?
}