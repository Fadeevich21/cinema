package com.example.cinema.data

import com.example.cinema.data.db.entities.MovieEntity
import com.example.cinema.data.db.entities.PlaceEntity
import com.example.cinema.data.db.entities.TicketEntity
import com.example.cinema.domain.model.MoviePoster
import com.example.cinema.domain.model.Place
import com.example.cinema.domain.model.Ticket

fun MovieEntity.toDomain() = MoviePoster(
    id = id,
    name = name,
    description = description,
    duration = duration
)

fun PlaceEntity.toDomain() = Place(
    id = id,
    row = row,
    seat = seat
)

fun TicketEntity.toDomain() = Ticket(
    id = id,
    movie = movie.toDomain(),
    place = place.toDomain()
)