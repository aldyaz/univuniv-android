package com.aldyaz.univuniv.utils

import com.aldyaz.univuniv.core.util.CoroutinesContextProvider
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlin.coroutines.CoroutineContext

class CoroutineTestDispatcherProvider(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : CoroutinesContextProvider {

    override val io: CoroutineContext
        get() = testDispatcher

    override val main: CoroutineContext
        get() = testDispatcher
}