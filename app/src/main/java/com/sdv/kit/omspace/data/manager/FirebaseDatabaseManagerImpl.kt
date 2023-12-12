package com.sdv.kit.omspace.data.manager

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sdv.kit.omspace.domain.manager.FirebaseDatabaseManager
import com.sdv.kit.omspace.domain.model.FirebaseIndexes
import com.sdv.kit.omspace.util.FirebaseReference

class FirebaseDatabaseManagerImpl : FirebaseDatabaseManager {

    private val database: FirebaseDatabase = Firebase.database

    override fun observeReference(reference: String, action: (DataSnapshot) -> Unit) {
        val eventListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                action(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {
                throw RuntimeException(error.message)
            }
        }

        getReference(reference).addValueEventListener(eventListener)
    }

    override fun <T> saveValue(reference: String, value: T) {
        getReference(reference).setValue(value)
    }

    override fun updateIndexes(indexes: FirebaseIndexes) {
        getReference(FirebaseReference.INDEXES).setValue(indexes)
    }

    private fun getReference(reference: String): DatabaseReference {
        return database.getReference(reference)
    }
}