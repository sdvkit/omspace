package com.sdv.kit.omspace.di

import com.sdv.kit.omspace.data.auth.GoogleAuthManager
import com.sdv.kit.omspace.data.repository.UserRepositoryImpl
import com.sdv.kit.omspace.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        googleAuthManager: GoogleAuthManager
    ): UserRepository = UserRepositoryImpl(googleAuthManager)
}