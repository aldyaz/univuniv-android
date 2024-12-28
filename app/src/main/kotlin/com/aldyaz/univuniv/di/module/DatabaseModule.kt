package com.aldyaz.univuniv.di.module

import android.content.Context
import com.aldyaz.univuniv.source.local.UnivUnivDatabase
import com.aldyaz.univuniv.source.local.dao.UniversityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideUnivUnivDatabase(
        @ApplicationContext context: Context
    ): UnivUnivDatabase = UnivUnivDatabase.instance(context)

    @Provides
    fun provideUniversityDao(
        database: UnivUnivDatabase
    ): UniversityDao = database.universityDao()

}