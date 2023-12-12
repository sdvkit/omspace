package com.sdv.kit.omspace.di

import com.sdv.kit.omspace.data.remote.api.DropboxApi
import com.sdv.kit.omspace.data.remote.api.DropboxAuthApi
import com.sdv.kit.omspace.data.remote.client.DropboxApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideDropboxApiClient(): DropboxApiClient =
        DropboxApiClient

    @Provides
    @Singleton
    fun provideDropboxAuthApi(
        apiClient: DropboxApiClient
    ): DropboxAuthApi = apiClient.authApiClient.create()

    @Provides
    @Singleton
    fun provideDropboxApi(
        apiClient: DropboxApiClient
    ): DropboxApi = apiClient.apiClient.create()
}