package com.sdv.kit.omspace.data.repository

import com.sdv.kit.omspace.data.auth.GoogleAuthManager
import com.sdv.kit.omspace.domain.model.UserData
import com.sdv.kit.omspace.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val googleAuthManager: GoogleAuthManager
) : UserRepository {

    override suspend fun getGoogleSignedInUser(): UserData? {
        return googleAuthManager.getSignedInUser()
    }
}