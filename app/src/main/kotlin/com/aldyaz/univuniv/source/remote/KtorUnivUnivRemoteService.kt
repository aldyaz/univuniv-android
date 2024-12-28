package com.aldyaz.univuniv.source.remote

import com.aldyaz.univuniv.core.network.apiCall
import com.aldyaz.univuniv.source.remote.model.UniversityDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class KtorUnivUnivRemoteService(
    private val httpClient: HttpClient
) : UnivUnivRemoteService {

    override suspend fun getUniversities(): List<UniversityDto> {
        return apiCall {
            httpClient.get("")
        }
    }
}