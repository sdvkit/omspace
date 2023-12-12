package com.sdv.kit.omspace.domain.model

import com.sdv.kit.omspace.util.DROPBOX_AUTHORIZE_URI
import com.sdv.kit.omspace.util.DROPBOX_CLIENT_ID
import com.sdv.kit.omspace.util.DROPBOX_REDIRECT_URI
import com.sdv.kit.omspace.util.DROPBOX_RESPONSE_TYPE
import com.sdv.kit.omspace.util.DROPBOX_SCOPES
import com.sdv.kit.omspace.util.DROPBOX_TOKEN_BASE_URL

enum class StorageType(
    var storageName: String = "",
    var storageIcon: String = "",
    val redirectUri: String = "",
    val authorizeUri: String = "",
    val tokenUri: String = "",
    val clientId: String = "",
    val responseType: String = "",
    val scopes: String = ""
) {

    NONE,
    DROPBOX(
        storageName = "Dropbox",
        storageIcon = "https://firebasestorage.googleapis.com/v0/b/omspace-d0957.appspot.com/o/icons%2Flogos%2Fic_dropbox_512.png?alt=media&token=49d0d191-9aba-43a8-a3ad-ec108fd0e47d",
        redirectUri = DROPBOX_REDIRECT_URI,
        authorizeUri = DROPBOX_AUTHORIZE_URI,
        tokenUri = DROPBOX_TOKEN_BASE_URL,
        clientId = DROPBOX_CLIENT_ID,
        responseType = DROPBOX_RESPONSE_TYPE,
        scopes = DROPBOX_SCOPES
    );

    companion object {
        fun nameOf(storageName: String): StorageType {
            return values().find { it.storageName == storageName }!!
        }

        fun notEmptyValues(): List<StorageType> {
            return values().filter { it.storageName.isNotEmpty() }
        }
    }
}