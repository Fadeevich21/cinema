package com.example.cinema.data.db.daos

import com.example.cinema.data.db.entities.MovieEntity
import com.example.cinema.data.db.tables.MoviesTable
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class MoviesDao(database: Database) : Dao(database) {
    private val movies get() = database.sequenceOf(MoviesTable)

    fun getAllMovies(): List<MovieEntity> {
        return movies.toList()
    }

    fun getMovieById(id: Int): MovieEntity {
        return movies.filter { it.id eq id }.toList()[0]
    }
}