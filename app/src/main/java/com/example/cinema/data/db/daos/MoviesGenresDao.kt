package com.example.cinema.data.db.daos

import com.example.cinema.data.db.entities.GenreEntity
import com.example.cinema.data.db.tables.MoviesGenresTable
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class MoviesGenresDao(database: Database) : Dao(database) {
    private val moviesGenres get() = database.sequenceOf(MoviesGenresTable)

    fun getGenresByMovieId(movieId: Int): List<GenreEntity> {
        return moviesGenres.filter { it.movieId eq movieId }.toList().map { it.genreId }
    }
}