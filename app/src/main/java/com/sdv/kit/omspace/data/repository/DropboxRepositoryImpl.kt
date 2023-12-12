package com.sdv.kit.omspace.data.repository

import com.sdv.kit.omspace.data.remote.api.DropboxApi
import com.sdv.kit.omspace.data.remote.api.DropboxAuthApi
import com.sdv.kit.omspace.data.remote.dto.AccessToken
import com.sdv.kit.omspace.domain.manager.FirebaseDatabaseManager
import com.sdv.kit.omspace.domain.mapper.UserDataMapper
import com.sdv.kit.omspace.domain.model.UserData
import com.sdv.kit.omspace.domain.model.UserStorage
import com.sdv.kit.omspace.domain.repository.StorageRepository
import com.sdv.kit.omspace.domain.usecase.auth.google.GetSignedInGoogleUser
import com.sdv.kit.omspace.util.BEARER
import com.sdv.kit.omspace.util.FirebaseUtils
import javax.inject.Inject

class DropboxRepositoryImpl @Inject constructor(
    private val dropboxAuthApi: DropboxAuthApi,
    private val dropboxApi: DropboxApi,
    private val firebaseDatabaseManager: FirebaseDatabaseManager,
    private val getSignedInGoogleUser: GetSignedInGoogleUser,
    private val userDataMapper: UserDataMapper
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

    override suspend fun saveUserStorageConnection(userStorage: UserStorage): Result<UserStorage> {
        return try {
            val userResult = getSignedInGoogleUser()

            if (userResult.isFailure) {
                throw RuntimeException()
            }

            val reference = FirebaseUtils.getUserStoragesReference(userResult.getOrThrow().userId)
            firebaseDatabaseManager.pushValue(reference, userStorage)
            Result.success(userStorage)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCurrentUserData(accessToken: AccessToken): Result<UserData> {
        return try {
            val authorization = BEARER + accessToken.value
            val dropboxUserData = dropboxApi.getCurrentAccount(authorization)
            Result.success(userDataMapper.toUserData(dropboxUserData))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}