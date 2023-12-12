package com.sdv.kit.omspace.data.mapper

import com.google.firebase.database.DataSnapshot
import com.sdv.kit.omspace.data.remote.dto.AccessToken
import com.sdv.kit.omspace.domain.mapper.FirebaseSnapshotMapper
import com.sdv.kit.omspace.domain.model.StorageConnection
import com.sdv.kit.omspace.domain.model.StorageOwner
import com.sdv.kit.omspace.domain.model.UserStorage

class FirebaseSnapshotMapperImpl : FirebaseSnapshotMapper {

    override fun toUserStoragesList(snapshot: DataSnapshot): List<UserStorage> {
        if (snapshot.value == null) {
            return emptyList()
        }

        val result: MutableList<UserStorage> = mutableListOf()
        val userStoragesList = snapshot.value as HashMap<*, *>

        userStoragesList.values.forEach { userStorageMap ->
            val storage = fromMapToUserStorage(userStorageMap as HashMap<*, *>)
            result.add(storage)
        }

        return result
    }

    private fun fromMapToUserStorage(userStorageMap: HashMap<*, *>): UserStorage {
        return UserStorage(
            storageTypeName = userStorageMap["storageTypeName"].toString(),
            storageConnection = fromMapToStorageConnection(userStorageMap["storageConnection"] as HashMap<*, *>),
            storageOwner = fromMapToStorageOwner(userStorageMap["storageOwner"] as HashMap<*, *>),
            totalCapacity = userStorageMap["totalCapacity"].toString().toFloat(),
            occupiedSpace = userStorageMap["occupiedSpace"].toString().toFloat()
        )
    }

    private fun fromMapToStorageConnection(storageConnectionMap: HashMap<*, *>): StorageConnection {
        val accessTokenMap = storageConnectionMap["accessToken"] as HashMap<*, *>

        return StorageConnection(
            accessToken = AccessToken(accessTokenMap["value"].toString())
        )
    }

    private fun fromMapToStorageOwner(storageOwnerMap: HashMap<*, *>): StorageOwner {
        return StorageOwner(
            storageOwnerId = storageOwnerMap["storageOwnerId"].toString()
        )
    }
}