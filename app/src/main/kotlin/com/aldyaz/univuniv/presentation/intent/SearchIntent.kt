package com.aldyaz.univuniv.presentation.intent

sealed class SearchIntent {

    data class Fetch(
        val query: String
    ) : SearchIntent()
}