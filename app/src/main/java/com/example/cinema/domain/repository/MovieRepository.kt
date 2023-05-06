package com.example.cinema.domain.repository

import android.net.Uri
import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.model.User
import com.fasterxml.jackson.databind.JsonNode

interface MovieRepository {

    fun getMovieById(id: Int): Movie
    fun getAllMovies(): List<Movie>
    fun filterMoviesByName(movies: List<Movie>, name: String): List<Movie>

    fun buyMovie(user: User, movie: Movie): Boolean
    fun getBoughtMoviesByUser(user: User): List<Movie>
    fun checkBoughtMovieByUser(user: User, movie: Movie): Boolean

    fun addMovie(movie: Movie): Boolean
    fun deleteMovie(movie: Movie): Boolean

    fun getMovieJson(movie: Movie): JsonNode

    fun saveMovieJson(uri: Uri?, movie: Movie)
    fun saveMovieCsv(uri: Uri?, movie: Movie)
}