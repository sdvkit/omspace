package com.sdv.kit.omspace.data.manager

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.sdv.kit.omspace.data.remote.dto.AccessToken
import com.sdv.kit.omspace.domain.manager.LocalStorageManager
import com.sdv.kit.omspace.domain.model.StorageType
import com.sdv.kit.omspace.domain.model.StorageTypeWrapper
import com.sdv.kit.omspace.util.LOCAL_STORAGE_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalStorageManagerImpl @Inject constructor(
    private val application: Application
) : LocalStorageManager {

    override suspend fun saveAccessToken(
        storageType: StorageType,
        token: AccessToken
    ): Result<AccessToken> {
        application.dataStore.edit { settings ->
            val name = getStorageTypeName(storageType)
            val key = keys.find { it.name == name }!!
            settings[key] = serializeAccessToken(token)
        }
        return Result.success(token)
    }

    override suspend fun readAccessToken(storageType: StorageType): Flow<Result<AccessToken>> {
        return application.dataStore.data.map { preferences ->
            val name = getStorageTypeName(storageType)
            val key = keys.find { it.name == name }!!

            try {
                val token = deserializeAccessToken(preferences[key]!!)
                return@map Result.success(token)
            } catch (e: Exception) {
                return@map Result.failure(e)
            }
        }
    }

    private fun serializeAccessToken(accessToken: AccessToken): String {
        return Gson().toJson(accessToken)
    }

    private fun deserializeAccessToken(tokenValue: String): AccessToken {
        return Gson().fromJson(tokenValue, AccessToken::class.java)
    }

    private fun getStorageTypeName(storageType: StorageType): String {
        val storageTypeWrapper = StorageTypeWrapper.values().find {
            it.type::class.java == storageType::class.java
        }

        return storageTypeWrapper!!.name
    }
}

private val readOnlyProperty = preferencesDataStore(name = LOCAL_STORAGE_SETTINGS)

val Context.dataStore: DataStore<Preferences> by readOnlyProperty

private val keys: List<Preferences.Key<String>> by lazy {
    StorageTypeWrapper.values().map { storageTypeWrapper ->
        stringPreferencesKey(storageTypeWrapper.name)
    }
}