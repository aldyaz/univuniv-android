package com.aldyaz.univuniv.di.module

import com.aldyaz.univuniv.data.local.UniversityLocalDataSource
import com.aldyaz.univuniv.data.local.UniversityLocalDataSourceImpl
import com.aldyaz.univuniv.data.remote.UniversityRemoteDataSource
import com.aldyaz.univuniv.data.remote.UniversityRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindUniversityLocalDataSource(
        impl: UniversityLocalDataSourceImpl
    ): UniversityLocalDataSource

    @Binds
    abstract fun bindUniversityRemoteDataSource(
        impl: UniversityRemoteDataSourceImpl
    ): UniversityRemoteDataSource

}