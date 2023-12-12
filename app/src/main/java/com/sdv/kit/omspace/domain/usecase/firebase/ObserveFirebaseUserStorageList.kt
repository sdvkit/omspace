package com.sdv.kit.omspace.domain.usecase.firebase

import com.sdv.kit.omspace.data.remote.api.DropboxApi
import com.sdv.kit.omspace.domain.manager.FirebaseDatabaseManager
import com.sdv.kit.omspace.domain.mapper.FirebaseSnapshotMapper
import com.sdv.kit.omspace.domain.model.StorageType
import com.sdv.kit.omspace.domain.model.UserStorage
import com.sdv.kit.omspace.util.BEARER
import com.sdv.kit.omspace.util.FirebaseUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ObserveFirebaseUserStorageList @Inject constructor (
    private val firebaseDatabaseManager: FirebaseDatabaseManager,
    private val firebaseSnapshotMapper: FirebaseSnapshotMapper,
    private val dropboxApi: DropboxApi
) {

    operator fun invoke(
        uid: String,
        action: (List<UserStorage>) -> Unit
    ) {
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        firebaseDatabaseManager.observeReference(FirebaseUtils.getUserStoragesReference(uid)) { snapshot ->
            coroutineScope.launch {
                val storages = firebaseSnapshotMapper.toUserStoragesList(snapshot)
                storages.forEach { storage -> setStorageOwnerUsername(storage) }
                action(storages)
            }
        }
    }

    private suspend fun setStorageOwnerUsername(storage: UserStorage) {
        val storageType = StorageType.nameOf(storage.storageTypeName)
        val accessToken = storage.storageConnection.accessToken
        val authorization = BEARER + accessToken.value

        return when (storageType) {
            StorageType.DROPBOX -> {
                val userData = dropboxApi.getCurrentAccount(authorization)
                storage.storageOwner.storageOwnerUsername = userData.name.displayName
            }

            else -> {
                throw RuntimeException("Something goes wrong with StorageType $storageType")
            }
        }
    }
}