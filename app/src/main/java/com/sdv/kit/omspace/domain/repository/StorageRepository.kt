package com.sdv.kit.omspace.domain.repository

import com.sdv.kit.omspace.data.remote.dto.AccessToken
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    suspend fun getAccessToken(authCode: String, codeVerifier: String): Result<AccessToken>
    suspend fun saveLocalAccessToken(token: AccessToken): Result<AccessToken>
    suspend fun readLocalAccessToken(): Flow<Result<AccessToken>>
}