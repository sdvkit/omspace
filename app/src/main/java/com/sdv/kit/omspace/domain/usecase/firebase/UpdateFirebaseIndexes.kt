package com.sdv.kit.omspace.domain.usecase.firebase

import com.sdv.kit.omspace.domain.manager.FirebaseDatabaseManager
import com.sdv.kit.omspace.domain.model.FirebaseIndexes
import javax.inject.Inject

class UpdateFirebaseIndexes @Inject constructor(
    private val firebaseDatabaseManager: FirebaseDatabaseManager
) {

    operator fun invoke(indexes: FirebaseIndexes) {
        firebaseDatabaseManager.updateIndexes(indexes)
    }
}