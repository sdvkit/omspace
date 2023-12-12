package com.sdv.kit.omspace.data.remote.client

import com.sdv.kit.omspace.util.DROPBOX_BASE_URL
import com.sdv.kit.omspace.util.DROPBOX_TOKEN_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DropboxApiClient {
    val apiClient: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(DROPBOX_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authApiClient: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(DROPBOX_TOKEN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}