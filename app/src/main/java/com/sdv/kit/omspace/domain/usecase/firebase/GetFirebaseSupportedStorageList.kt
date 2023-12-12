package com.sdv.kit.omspace.domain.usecase.firebase

import com.sdv.kit.omspace.domain.manager.FirebaseDatabaseManager
import com.sdv.kit.omspace.domain.mapper.FirebaseSnapshotMapper
import com.sdv.kit.omspace.util.FirebaseReference
import com.sdv.kit.omspace.domain.model.SupportedStorage
import javax.inject.Inject

class GetFirebaseSupportedStorageList @Inject constructor (
    private val firebaseDatabaseManager: FirebaseDatabaseManager,
    private val firebaseSnapshotMapper: FirebaseSnapshotMapper
) {

    operator fun invoke(action: (List<SupportedStorage>) -> Unit) {
        firebaseDatabaseManager.observeReference(FirebaseReference.SUPPORTED_STORAGES) { snapshot ->
            val storages = firebaseSnapshotMapper.toSupportedStoragesList(snapshot)
            action(storages)
        }
    }
}