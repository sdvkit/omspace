package com.sdv.kit.omspace.util

object FirebaseReference {
    const val SUPPORTED_STORAGES = "supportedStorages"
    const val INDEXES = "service/indexes"
//    const val NEXT_SUPPORTED_STORAGE_ID = "service/indexes/nextSupportedStorageId"
//    const val NEXT_USER_STORAGE_ID = "service/indexes/nextUserStorageId"

    fun getUserStorages(uid: String): String = "users/$uid/storages"

    fun getConnections(uid: String): String = "users/$uid/connections"
}
