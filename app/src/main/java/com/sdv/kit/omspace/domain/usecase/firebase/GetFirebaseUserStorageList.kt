package com.sdv.kit.omspace.domain.usecase.firebase

import com.sdv.kit.omspace.domain.manager.FirebaseDatabaseManager
import com.sdv.kit.omspace.domain.mapper.FirebaseSnapshotMapper
import com.sdv.kit.omspace.domain.model.UserStorage
import com.sdv.kit.omspace.util.FirebaseReference
import javax.inject.Inject

class GetFirebaseUserStorageList @Inject constructor (
    private val firebaseDatabaseManager: FirebaseDatabaseManager,
    private val firebaseSnapshotMapper: FirebaseSnapshotMapper
) {

    operator fun invoke(
        uid: String,
        transform: (UserStorage) -> Unit,
        action: (List<UserStorage>) -> Unit
    ) {
        firebaseDatabaseManager.observeReference(FirebaseReference.getUserStorages(uid)) { snapshot ->
            val storages = firebaseSnapshotMapper.toUserStoragesList(snapshot)
            storages.forEach { storage -> transform(storage) }
            action(storages)
        }
    }
}