package com.example.cinema.data.db.entities

import org.ktorm.entity.Entity

interface MovieGenreEntity : Entity<MovieGenreEntity> {

    companion object : Entity.Factory<MovieGenreEntity>()

    var id: Int
    var movieId: MovieEntity
    var genreId: GenreEntity
}