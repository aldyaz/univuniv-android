package com.aldyaz.univuniv.core.domain

abstract class DomainException(
    cause: Throwable? = null
) : Exception(cause)