package com.aldyaz.univuniv.core.domain

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<PARAM, RESULT> {

    abstract fun execute(param: PARAM): Flow<RESULT>

    operator fun invoke(param: PARAM) = execute(param)

}