package com.example.cinema.data.db.daos

import com.example.cinema.data.db.entities.MovieEntity
import com.example.cinema.data.db.entities.UserEntity
import com.example.cinema.data.db.entities.UserMovieEntity
import com.example.cinema.data.db.tables.UsersMoviesTable
import com.example.cinema.data.db.utils.Dao
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.any
import org.ktorm.entity.filter
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class UsersMoviesDao(database: Database) : Dao(database = database) {

    private val usersMovies = database.sequenceOf(UsersMoviesTable)

    private fun getCountRecords(): Int {
        return database.sequenceOf(UsersMoviesTable).totalRecords
    }

    fun addRecord(user: UserEntity, movie: MovieEntity): Boolean {
        val userMovie = UserMovieEntity {
            id = getCountRecords()
            userId = user
            movieId = movie
        }
        usersMovies.add(userMovie)

        // TODO
        return true
    }

    fun getMoviesByUser(user: UserEntity): List<MovieEntity> {
        return usersMovies.filter { it.userId eq user.id }.toList().map { it.movieId }
    }

    fun checkMovieByUser(user: UserEntity, movie: MovieEntity): Boolean {
        return usersMovies.any {
            it.userId eq user.id
            it.movieId eq movie.id
        }
    }
}