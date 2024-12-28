package com.aldyaz.univuniv.presentation.intent

sealed class HomeIntent {

    data object Retry : HomeIntent()
}
