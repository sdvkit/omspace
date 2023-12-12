package com.sdv.kit.omspace.domain.usecase.auth

import com.sdv.kit.omspace.data.remote.dto.AccessToken
import com.sdv.kit.omspace.data.repository.DropboxRepositoryImpl
import com.sdv.kit.omspace.domain.model.StorageType
import javax.inject.Inject

class GetAccessToken @Inject constructor(
    private val dropboxRepositoryImpl: DropboxRepositoryImpl
) {

    suspend operator fun invoke(
        storageType: StorageType,
        authCode: String,
        codeVerifier: String
    ): Result<AccessToken> {
        return when (storageType) {
            StorageType.DROPBOX -> {
                dropboxRepositoryImpl.getAccessToken(
                    authCode = authCode,
                    codeVerifier = codeVerifier
                )
            }

            else -> Result.failure(
                RuntimeException("Something goes wrong with StorageType $storageType")
            )
        }
    }
}