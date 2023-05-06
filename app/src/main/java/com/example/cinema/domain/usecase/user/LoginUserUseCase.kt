package com.example.cinema.domain.usecase.user

import com.example.cinema.domain.model.User
import com.example.cinema.domain.repository.UserRepository

class LoginUserUseCase(private val repository: UserRepository) {

    fun execute(username: String, password: String): User? {
        return repository.loginUser(username = username, password = password)
    }
}