package com.sdv.kit.omspace.domain.usecase.firebase

import com.sdv.kit.omspace.data.remote.dto.AccessToken
import com.sdv.kit.omspace.domain.manager.FirebaseDatabaseManager
import com.sdv.kit.omspace.domain.model.FirebaseIndexes
import com.sdv.kit.omspace.domain.model.StorageConnection
import com.sdv.kit.omspace.domain.model.StorageType
import com.sdv.kit.omspace.domain.model.StorageTypeWrapper
import com.sdv.kit.omspace.domain.repository.UserRepository
import com.sdv.kit.omspace.util.FirebaseReference
import javax.inject.Inject

class SaveFirebaseStorageConnection @Inject constructor(
    private val userRepository: UserRepository,
    private val firebaseDatabaseManager: FirebaseDatabaseManager
) {

    suspend operator fun invoke(
        storageType: StorageType,
        accessToken: AccessToken,
        firebaseIndexes: FirebaseIndexes
    ) {
        val googleUser = userRepository.getGoogleSignedInUser()
        val reference = FirebaseReference.getConnections(googleUser!!.userId)

        val connection = StorageConnection(
            accessToken = accessToken,
            typeName = StorageTypeWrapper.valueOf(storageType).name
        )

        firebaseDatabaseManager.saveValue("$reference/${firebaseIndexes.nextConnectionId}", connection)
        firebaseDatabaseManager.updateIndexes(
            firebaseIndexes.copy(nextConnectionId = firebaseIndexes.nextConnectionId.inc())
        )
    }
}