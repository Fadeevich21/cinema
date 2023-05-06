package com.example.cinema.domain.usecase.model

import com.example.cinema.domain.usecase.user.CheckUserHasPrivilegeUseCase
import com.example.cinema.domain.usecase.user.LoginUserUseCase
import com.example.cinema.domain.usecase.user.RegisterUserUseCase

data class UserUseCases(
    val checkUserHasPrivilegeUseCase: CheckUserHasPrivilegeUseCase,
    val loginUserUseCase: LoginUserUseCase,
    val registerUserUseCase: RegisterUserUseCase
)