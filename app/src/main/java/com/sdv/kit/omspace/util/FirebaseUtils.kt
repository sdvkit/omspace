package com.sdv.kit.omspace.util

object FirebaseUtils {
    fun getUserStoragesReference(uid: String): String = "users/$uid/storages"
}
