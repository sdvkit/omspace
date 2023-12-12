package com.sdv.kit.omspace.presentation

import com.sdv.kit.omspace.data.remote.dto.AccessToken
import com.sdv.kit.omspace.domain.model.StorageConnection
import com.sdv.kit.omspace.domain.model.StorageOwner
import com.sdv.kit.omspace.domain.model.UserStorage

object SampleData {
    val userStorages: List<UserStorage> by lazy {
        listOf(
            UserStorage(
                storageTypeName = "Dropbox",
                storageConnection = StorageConnection(
                    accessToken = AccessToken("FFF")
                ),
                storageOwner = StorageOwner(
                    storageOwnerId = "ID1",
                    storageOwnerUsername = "sdvkit"
                ),
                totalCapacity = 2f,
                occupiedSpace = 0f
            ),
            UserStorage(
                storageTypeName = "Google Drive",
                storageConnection = StorageConnection(
                    accessToken = AccessToken("EEE")
                ),
                storageOwner = StorageOwner(
                    storageOwnerId = "ID2",
                    storageOwnerUsername = "Mikola Drive"
                ),
                totalCapacity = 15f,
                occupiedSpace = 0f
            )
        )
    }
}