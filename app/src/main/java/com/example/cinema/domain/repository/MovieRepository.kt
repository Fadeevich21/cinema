package com.example.cinema.domain.repository

import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.model.User

interface MovieRepository {

    fun getMovieById(id: Int): Movie
    fun getAllMovies(): List<Movie>
    fun filterMoviesByName(movies: List<Movie>, name: String): List<Movie>

    fun buyMovie(user: User, movie: Movie): Boolean
    fun getBoughtMoviesByUser(user: User): List<Movie>
    fun checkBoughtMovieByUser(user: User, movie: Movie): Boolean

    fun addMovie(movie: Movie): Boolean
    fun deleteMovie(movie: Movie): Boolean
}