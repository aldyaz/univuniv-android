package com.aldyaz.univuniv.di.module

import com.aldyaz.univuniv.core.presentation.ExceptionToPresentationMapper
import com.aldyaz.univuniv.presentation.mapper.UniversityToPresentationMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class PresentationModule {

    @Provides
    fun provideExceptionToPresentationMapper(): ExceptionToPresentationMapper =
        ExceptionToPresentationMapper()

    @Provides
    fun provideUniversityToPresentationMapper(): UniversityToPresentationMapper =
        UniversityToPresentationMapper()

}
