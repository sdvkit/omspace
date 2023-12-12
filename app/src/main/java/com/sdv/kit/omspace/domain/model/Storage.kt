package com.sdv.kit.omspace.domain.model

import com.google.firebase.database.Exclude
import com.sdv.kit.omspace.data.remote.dto.AccessToken

data class UserStorage(
    val storageTypeName: String,
    val storageConnection: StorageConnection,
    val storageOwner: StorageOwner,
    val totalCapacity: Float,
    val occupiedSpace: Float
)

data class StorageConnection(
    val accessToken: AccessToken
)

data class StorageOwner(
    val storageOwnerId: String,
    @Exclude
    var storageOwnerUsername: String = ""
)