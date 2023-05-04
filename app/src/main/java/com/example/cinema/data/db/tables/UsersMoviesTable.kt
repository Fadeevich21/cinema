package com.example.cinema.data.db.tables

import com.example.cinema.data.db.entities.UserMovieEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

object UsersMoviesTable : Table<UserMovieEntity>("users_movies") {

    val id = int("user_movie_id").primaryKey().bindTo { it.id }
    val username = text("user_id").references(UsersTable) { it.username }
    val movieId = int("movie_id").references(MoviesTable) { it.movieId }
}