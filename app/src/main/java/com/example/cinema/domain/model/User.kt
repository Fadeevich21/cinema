package com.example.cinema.domain.model


data class User(

    val id: Int,
    var name: String,
    var login: String,
    var password: String,
    val role: Role
)