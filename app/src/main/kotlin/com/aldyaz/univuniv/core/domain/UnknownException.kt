package com.aldyaz.univuniv.core.domain

class UnknownException(
    override val message: String = "Unknown exception!"
) : DomainException()