package com.sdv.kit.omspace.data.repository

import com.sdv.kit.omspace.data.remote.api.DropboxAuthApi
import com.sdv.kit.omspace.data.remote.dto.AccessToken
import com.sdv.kit.omspace.domain.manager.LocalStorageManager
import com.sdv.kit.omspace.domain.model.StorageType
import com.sdv.kit.omspace.domain.repository.StorageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DropboxRepositoryImpl @Inject constructor(
    private val dropboxAuthApi: DropboxAuthApi,
    private val localStorageManager: LocalStorageManager
) : StorageRepository {

    override suspend fun getAccessToken(
        authCode: String,
        codeVerifier: String
    ): Result<AccessToken> {
        return try {
            val token = dropboxAuthApi.getAccessToken(
                code = authCode,
                codeVerifier = codeVerifier
            )

            Result.success(token)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveLocalAccessToken(token: AccessToken): Result<AccessToken> {
        return localStorageManager.saveAccessToken(
            storageType = StorageType.Dropbox(),
            token = token
        )
    }

    override suspend fun readLocalAccessToken(): Flow<Result<AccessToken>> {
        return localStorageManager.readAccessToken(
            storageType = StorageType.Dropbox()
        )
    }
}