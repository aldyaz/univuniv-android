package com.aldyaz.univuniv.di.module

import com.aldyaz.univuniv.core.network.HttpClientFactory
import com.aldyaz.univuniv.source.remote.KtorUnivUnivRemoteService
import com.aldyaz.univuniv.source.remote.UnivUnivRemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient = HttpClientFactory().create()

    @Singleton
    @Provides
    fun provideUnivUnivRemoteService(
        httpClient: HttpClient
    ): UnivUnivRemoteService = KtorUnivUnivRemoteService(httpClient)
}