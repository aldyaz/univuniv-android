package com.aldyaz.univuniv.core.domain.exception

class UnknownException(
    override val message: String = "Unknown exception!"
) : DomainException()