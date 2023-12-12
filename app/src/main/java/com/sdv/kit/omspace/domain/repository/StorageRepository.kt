package com.sdv.kit.omspace.domain.repository

import com.sdv.kit.omspace.data.remote.dto.AccessToken
import com.sdv.kit.omspace.domain.model.UserData
import com.sdv.kit.omspace.domain.model.UserStorage

interface StorageRepository {
    suspend fun getAccessToken(authCode: String, codeVerifier: String): Result<AccessToken>
    suspend fun saveUserStorageConnection(userStorage: UserStorage): Result<UserStorage>
    suspend fun getCurrentUserData(accessToken: AccessToken): Result<UserData>
}