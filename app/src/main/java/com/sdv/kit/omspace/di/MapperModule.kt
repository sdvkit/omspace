package com.sdv.kit.omspace.di

import com.sdv.kit.omspace.data.mapper.FirebaseSnapshotMapperImpl
import com.sdv.kit.omspace.data.mapper.UserDataMapperImpl
import com.sdv.kit.omspace.domain.mapper.FirebaseSnapshotMapper
import com.sdv.kit.omspace.domain.mapper.UserDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    @Singleton
    fun provideFirebaseSnapshotMapper(): FirebaseSnapshotMapper =
        FirebaseSnapshotMapperImpl()

    @Provides
    @Singleton
    fun provideUserDataMapper(): UserDataMapper =
        UserDataMapperImpl()
}