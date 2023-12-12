package com.sdv.kit.omspace.domain.usecase.auth

import com.sdv.kit.omspace.data.remote.dto.AccessToken
import com.sdv.kit.omspace.data.repository.DropboxRepositoryImpl
import com.sdv.kit.omspace.domain.model.StorageType
import javax.inject.Inject

class SaveLocalAccessToken @Inject constructor(
    private val dropboxRepository: DropboxRepositoryImpl
) {

    suspend operator fun invoke(
        storageType: StorageType,
        token: AccessToken
    ): Result<AccessToken> {
        return when (storageType) {
            is StorageType.Dropbox -> {
                dropboxRepository.saveLocalAccessToken(token)
            }

            else -> Result.failure(
                RuntimeException("Something goes wrong with StorageType $storageType")
            )
        }
    }
}