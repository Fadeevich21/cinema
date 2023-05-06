package com.example.cinema.domain.usecase.model

import com.example.cinema.domain.usecase.privilege.GetPrivilegeByNameUseCase

data class PrivilegeUseCases(
    val getPrivilegeByNameUseCase: GetPrivilegeByNameUseCase
)