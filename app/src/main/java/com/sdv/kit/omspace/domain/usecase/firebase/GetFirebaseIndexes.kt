package com.sdv.kit.omspace.domain.usecase.firebase

import com.sdv.kit.omspace.domain.manager.FirebaseDatabaseManager
import com.sdv.kit.omspace.domain.mapper.FirebaseSnapshotMapper
import com.sdv.kit.omspace.domain.model.FirebaseIndexes
import com.sdv.kit.omspace.util.FirebaseReference
import javax.inject.Inject

class GetFirebaseIndexes @Inject constructor(
    private val firebaseDatabaseManager: FirebaseDatabaseManager,
    private val firebaseSnapshotMapper: FirebaseSnapshotMapper
) {

    operator fun invoke(action: (FirebaseIndexes) -> Unit) {
        firebaseDatabaseManager.observeReference(FirebaseReference.INDEXES) { snapshot ->
            val firebaseIndexes = firebaseSnapshotMapper.toFirebaseIndexes(snapshot)
            action(firebaseIndexes)
        }
    }
}