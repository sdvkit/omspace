package com.sdv.kit.omspace.di

import com.sdv.kit.omspace.data.manager.FirebaseDatabaseManagerImpl
import com.sdv.kit.omspace.domain.manager.FirebaseDatabaseManager
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