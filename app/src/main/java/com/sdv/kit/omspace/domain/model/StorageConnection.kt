package com.sdv.kit.omspace.domain.model

import com.sdv.kit.omspace.data.remote.dto.AccessToken

data class StorageConnection(
    var accessToken: AccessToken,
    var typeName: String
)