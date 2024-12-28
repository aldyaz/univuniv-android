package com.aldyaz.univuniv.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<INTENT> : ViewModel() {

    abstract fun onIntent(intent: INTENT)

    protected fun <STATE> MutableStateFlow<STATE>.updateState(
        state: STATE.() -> STATE
    ) {
        update(state)
    }
}