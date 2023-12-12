package com.sdv.kit.omspace.domain.manager

import com.google.firebase.database.DataSnapshot
import com.sdv.kit.omspace.domain.model.FirebaseIndexes

interface FirebaseDatabaseManager {
    fun observeReference(reference: String, action: (DataSnapshot) -> Unit)
    fun <T> saveValue(reference: String, value: T)
    fun updateIndexes(indexes: FirebaseIndexes)
}