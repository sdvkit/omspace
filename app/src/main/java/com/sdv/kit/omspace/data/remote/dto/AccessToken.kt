package com.sdv.kit.omspace.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access_token") val value: String
)