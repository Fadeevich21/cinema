package com.example.cinema.domain.model

data class Ticket(
    var id: Int,
    var movie: MoviePoster,
    var place: Place
)