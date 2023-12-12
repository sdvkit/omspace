package com.sdv.kit.omspace.domain.manager

import com.google.firebase.database.DataSnapshot

interface FirebaseDatabaseManager {
    fun observeReference(reference: String, action: (DataSnapshot) -> Unit)
    fun <T> saveValue(reference: String, value: T)
    fun <T> pushValue(reference: String, value: T)
}