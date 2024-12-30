package com.aldyaz.univuniv.di.module

import com.aldyaz.univuniv.core.util.CoroutinesContextProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class CommonModule {

    @Provides
    fun provideCoroutinesContextProvider(): CoroutinesContextProvider =
        CoroutinesContextProvider.Default

}