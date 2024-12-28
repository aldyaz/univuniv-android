package com.aldyaz.univuniv.core.domain.exception

abstract class DomainException(
    cause: Throwable? = null
) : Exception(cause)