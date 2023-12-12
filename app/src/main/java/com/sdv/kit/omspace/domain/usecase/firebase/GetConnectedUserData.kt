package com.sdv.kit.omspace.domain.usecase.firebase

import com.sdv.kit.omspace.data.remote.dto.AccessToken
import com.sdv.kit.omspace.data.repository.DropboxRepositoryImpl
import com.sdv.kit.omspace.domain.model.StorageType
import com.sdv.kit.omspace.domain.model.UserData
import javax.inject.Inject

class GetConnectedUserData @Inject constructor(
    private val dropboxRepositoryImpl: DropboxRepositoryImpl
) {

    suspend operator fun invoke(
        storageType: StorageType,
        accessToken: AccessToken
    ): Result<UserData> {
        return when (storageType) {
            StorageType.DROPBOX -> {
                try {
                    val userDataResult = dropboxRepositoryImpl.getCurrentUserData(accessToken)
                    val userData = userDataResult.getOrThrow()
                    return Result.success(userData)
                } catch (e: Exception) {
                    return Result.failure(e)
                }
            }

            else -> {
                Result.failure(
                    RuntimeException("Something goes wrong with StorageType $storageType")
                )
            }
        }
    }
}