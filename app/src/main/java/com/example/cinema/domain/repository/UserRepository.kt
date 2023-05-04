package com.example.cinema.domain.repository

import com.example.cinema.domain.model.User

interface UserRepository {

    fun loginUser(username: String, password: String): User?
    fun registerUser(username: String, password: String): Boolean
}