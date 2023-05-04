package com.example.cinema.domain.usecase

import com.example.cinema.domain.repository.UserRepository

class RegisterUserUseCase(private val repository: UserRepository) {

    fun execute(username: String, password: String): Boolean {
        return repository.registerUser(username = username, password = password)
    }
}