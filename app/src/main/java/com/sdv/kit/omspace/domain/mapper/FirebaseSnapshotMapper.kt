package com.sdv.kit.omspace.domain.mapper

import com.google.firebase.database.DataSnapshot
import com.sdv.kit.omspace.domain.model.UserStorage

interface FirebaseSnapshotMapper {
    fun toUserStoragesList(snapshot: DataSnapshot): List<UserStorage>
}