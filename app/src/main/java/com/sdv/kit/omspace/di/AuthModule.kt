package com.sdv.kit.omspace.di

import android.app.Application
import com.sdv.kit.omspace.data.auth.ConnectCodeManagerImpl
import com.sdv.kit.omspace.data.auth.GoogleAuthManager
import com.sdv.kit.omspace.domain.auth.ConnectCodeManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.openid.appauth.AuthorizationService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideGoogleAuthManager(
        application: Application
    ): GoogleAuthManager = GoogleAuthManager(application)

    @Provides
    @Singleton
    fun provideAuthorizationService(
        application: Application
    ): AuthorizationService = AuthorizationService(application)

    @Provides
    @Singleton
    fun provideConnectCodeManager(): ConnectCodeManager =
        ConnectCodeManagerImpl()
}