package com.example.cinema.data.db.tables

import com.example.cinema.data.db.entities.UserMovieEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object UsersMoviesTable : Table<UserMovieEntity>("users_movies") {

    val id = int("user_movie_id").primaryKey().bindTo { it.id }
    val userId = int("user_id").references(UsersTable) { it.userId }
    val movieId = int("movie_id").references(MoviesTable) { it.movieId }
}