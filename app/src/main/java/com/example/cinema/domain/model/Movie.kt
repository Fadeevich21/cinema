package com.example.cinema.domain.model

data class Movie(
    var id: Int? = null,
    val name: String,
    val description: String,
    val duration: String,
    val yearOfRelease: Int,
    val ageRestriction: Int,
    val trailerUrl: String?,
    val contentUrl: String
)