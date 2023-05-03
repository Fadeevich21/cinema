package com.example.cinema.domain.model

data class Movie(
    val id: Int,
    val name: String,
    val description: String,
    val duration: String,
    val yearOfRelease: Int,
    val ageRestriction: Int,
    val trailerUrl: String?
)