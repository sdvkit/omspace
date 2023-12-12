package com.sdv.kit.omspace.domain.model

data class SupportedStorage(
    var id: Long = 0,
    var storageName: String = "",
    var storageIcon: String = "",
    var storageType: StorageType = StorageType.None
)

data class FirebaseSupportedStorage(
    var id: Long = 0,
    var storageName: String = "",
    var storageIcon: String = "",
    var typeName: String = ""
)

data class UserStorage(
    var id: Long = 0,
    var supportedStorage: SupportedStorage = SupportedStorage(),
    var storageOwnerUsername: String = "",
    var totalCapacity: Float = 0f,
    var occupiedSpace: Float = 0f
)

data class FirebaseUserStorage(
    var id: Long = 0,
    var supportedStorageId: Long = 0,
    var storageOwnerUsername: String = "",
    var totalCapacity: Float = 0f
)