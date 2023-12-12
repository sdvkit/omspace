package com.sdv.kit.omspace.domain.manager

import com.sdv.kit.omspace.data.remote.dto.AccessToken
import com.sdv.kit.omspace.domain.model.StorageType
import kotlinx.coroutines.flow.Flow

interface LocalStorageManager {
    suspend fun saveAccessToken(storageType: StorageType, token: AccessToken): Result<AccessToken>
    suspend fun readAccessToken(storageType: StorageType): Flow<Result<AccessToken>>
}