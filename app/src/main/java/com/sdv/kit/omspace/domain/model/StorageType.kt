package com.sdv.kit.omspace.domain.model

import com.sdv.kit.omspace.util.DROPBOX_AUTHORIZE_URI
import com.sdv.kit.omspace.util.DROPBOX_CLIENT_ID
import com.sdv.kit.omspace.util.DROPBOX_REDIRECT_URI
import com.sdv.kit.omspace.util.DROPBOX_RESPONSE_TYPE
import com.sdv.kit.omspace.util.DROPBOX_SCOPES
import com.sdv.kit.omspace.util.DROPBOX_TOKEN_BASE_URL

enum class StorageTypeWrapper(val type: StorageType) {
    NONE(StorageType.None),
    DROPBOX(StorageType.Dropbox());

    companion object {
        fun valueOf(type: StorageType): StorageTypeWrapper {
            return values().find { it.type.javaClass == type.javaClass }!!
        }
    }
}

sealed class StorageType {

    data object None : StorageType()

    class Dropbox(
        val redirectUri: String = DROPBOX_REDIRECT_URI,
        val authorizeUri: String = DROPBOX_AUTHORIZE_URI,
        val tokenUri: String = DROPBOX_TOKEN_BASE_URL,
        val clientId: String = DROPBOX_CLIENT_ID,
        val responseType: String = DROPBOX_RESPONSE_TYPE,
        val scopes: String = DROPBOX_SCOPES
    ) : StorageType()
}