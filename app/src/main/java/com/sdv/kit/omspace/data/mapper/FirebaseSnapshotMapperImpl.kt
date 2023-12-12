package com.sdv.kit.omspace.data.mapper

import com.google.firebase.database.DataSnapshot
import com.sdv.kit.omspace.domain.mapper.FirebaseSnapshotMapper
import com.sdv.kit.omspace.domain.model.FirebaseIndexes
import com.sdv.kit.omspace.domain.model.StorageTypeWrapper
import com.sdv.kit.omspace.domain.model.SupportedStorage
import com.sdv.kit.omspace.domain.model.UserStorage

class FirebaseSnapshotMapperImpl : FirebaseSnapshotMapper {

    override fun toSupportedStoragesList(snapshot: DataSnapshot): List<SupportedStorage> {
        val result: MutableList<SupportedStorage> = mutableListOf()
        val supportedStoragesList = snapshot.value as ArrayList<*>

        supportedStoragesList.forEach { supportedStorageMap ->
            val storage = fromMapToSupportedStorage(supportedStorageMap as Map<*, *>)
            result.add(storage)
        }

        return result
    }

    override fun toSupportedStorage(snapshot: DataSnapshot): SupportedStorage {
        return fromMapToSupportedStorage(snapshot.value as Map<*, *>)
    }

    private fun fromMapToSupportedStorage(supportedStorageMap: Map<*, *>): SupportedStorage {
        return SupportedStorage(
            id = supportedStorageMap["id"].toString().toLong(),
            storageName = supportedStorageMap["storageName"].toString(),
            storageIcon = supportedStorageMap["storageIcon"].toString(),
            storageType = StorageTypeWrapper.valueOf(supportedStorageMap["typeName"].toString()).type
        )
    }

    override fun toUserStoragesList(snapshot: DataSnapshot): List<UserStorage> {
        val result: MutableList<UserStorage> = mutableListOf()
        val userStoragesList = snapshot.value as ArrayList<*>

        userStoragesList.forEach { userStorageMap ->
            val storage = fromMapToUserStorage(userStorageMap as Map<*, *>)
            result.add(storage)
        }

        return result
    }

    override fun toFirebaseIndexes(snapshot: DataSnapshot): FirebaseIndexes {
        val firebaseIndexesMap = snapshot.value as HashMap<*, *>
        return FirebaseIndexes(
            nextConnectionId = firebaseIndexesMap["nextConnectionId"].toString().toLong(),
            nextSupportedStorageId = firebaseIndexesMap["nextSupportedStorageId"].toString().toLong(),
            nextUserStorageId = firebaseIndexesMap["nextUserStorageId"].toString().toLong()
        )
    }

    private fun fromMapToUserStorage(userStorageMap: Map<*, *>): UserStorage {
        return UserStorage(
            id = userStorageMap["id"].toString().toLong(),
            supportedStorage = SupportedStorage(id = userStorageMap["supportedStorageId"].toString().toLong()),
            storageOwnerUsername = userStorageMap["storageOwnerUsername"].toString(),
            totalCapacity = userStorageMap["totalCapacity"].toString().toFloat()
        )
    }
}