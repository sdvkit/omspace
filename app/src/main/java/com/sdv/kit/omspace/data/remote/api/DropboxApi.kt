package com.sdv.kit.omspace.data.remote.api

import com.sdv.kit.omspace.data.remote.dto.AccessToken
import com.sdv.kit.omspace.data.remote.dto.DropboxUserData
import com.sdv.kit.omspace.util.DROPBOX_CLIENT_ID
import com.sdv.kit.omspace.util.DROPBOX_CLIENT_SECRET
import com.sdv.kit.omspace.util.DROPBOX_REDIRECT_URI
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface DropboxAuthApi {

    @POST("token" +
            "?grant_type=authorization_code" +
            "&redirect_uri=$DROPBOX_REDIRECT_URI" +
            "&client_id=$DROPBOX_CLIENT_ID" +
            "&client_secret=$DROPBOX_CLIENT_SECRET")
    @Headers("Content-Type: application/json;charset=UTF-8")
    suspend fun getAccessToken(
        @Query("code") code: String,
        @Query("code_verifier") codeVerifier: String
    ): AccessToken
}

interface DropboxApi {

    @GET("users/get_current_account")
    @Headers("Content-Type: application/json;charset=UTF-8")
    suspend fun getCurrentAccount(
        @Header("Authorization") authorization: String
    ): DropboxUserData
}