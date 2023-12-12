package com.sdv.kit.omspace.di

import android.app.Application
import com.sdv.kit.omspace.data.manager.FirebaseDatabaseManagerImpl
import com.sdv.kit.omspace.data.manager.LocalStorageManagerImpl
import com.sdv.kit.omspace.domain.manager.FirebaseDatabaseManager
import com.sdv.kit.omspace.domain.manager.LocalStorageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ManagerModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabaseManager(): FirebaseDatabaseManager =
        FirebaseDatabaseManagerImpl()

    @Provides
    @Singleton
    fun provideLocalStorageManager(
        application: Application
    ): LocalStorageManager = LocalStorageManagerImpl(application)

//    @Provides
//    @Singleton
//    fun provideDatabaseClient(
//        application: Application
//    ): DatabaseClient = Room.databaseBuilder(
//        context = application,
//        klass = DatabaseClient::class.java,
//        name = null
//    ).build()
}