package com.example.cinema.data.repository

import android.net.Uri
import com.example.cinema.data.db.AppDatabase
import com.example.cinema.data.storage.MovieStorage
import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.model.User
import com.example.cinema.domain.repository.MovieRepository
import com.example.cinema.mapper.MovieMapper
import com.example.cinema.mapper.UserMapper
import com.fasterxml.jackson.databind.JsonNode
import java.util.Locale

class MovieRepositoryImpl(
    private val movieStorage: MovieStorage,
    private val movieMapper: MovieMapper,
    private val userMapper: UserMapper
) : MovieRepository {

    override fun getMovieById(id: Int): Movie {
        return movieMapper.mapFromEntity(AppDatabase.daos.moviesDao.getMovieById(id))
    }

    override fun getAllMovies(): List<Movie> {
        return movieMapper.fromEntityList(AppDatabase.daos.moviesDao.getAllMovies())
    }

    override fun filterMoviesByName(movies: List<Movie>, name: String): List<Movie> {
        val filteredMovies = mutableListOf<Movie>()
        for (movie in movies) {
            if (movie.name.lowercase().contains(name.lowercase(Locale.getDefault()))) {
                filteredMovies.add(movie)
            }
        }

        return filteredMovies
    }

    override fun buyMovie(user: User, movie: Movie): Boolean {
        return AppDatabase.daos.usersMoviesDao.addRecord(
            user = userMapper.mapToEntity(user),
            movie = movieMapper.mapToEntity(movie)
        )
    }

    override fun getBoughtMoviesByUser(user: User): List<Movie> {
        return movieMapper.fromEntityList(
            AppDatabase.daos.usersMoviesDao.getMoviesByUser(user = userMapper.mapToEntity(user))
        )
    }

    override fun checkBoughtMovieByUser(user: User, movie: Movie): Boolean {
        return AppDatabase.daos.usersMoviesDao.checkMovieByUser(
            user = userMapper.mapToEntity(user),
            movie = movieMapper.mapToEntity(movie)
        )
    }

    override fun addMovie(movie: Movie): Boolean {
        if (movie.id == null) {
            movie.id = AppDatabase.daos.moviesDao.getCountMovies() + 1
        }

        return AppDatabase.daos.moviesDao.addMovie(movie = movieMapper.mapToEntity(movie))
    }

    override fun deleteMovie(movie: Movie): Boolean {
        return AppDatabase.daos.moviesDao.deleteMovie(movie = movieMapper.mapToEntity(movie))
    }

    override fun getMovieJson(movie: Movie): JsonNode {
        return AppDatabase.daos.moviesDao.getJson(movie = movieMapper.mapToEntity(movie))
    }

    override fun saveMovieJson(uri: Uri?, movie: Movie) {
        movieStorage.saveMovieJson(uri = uri, jsonNode = getMovieJson(movie = movie))
    }

    override fun saveMovieCsv(uri: Uri?, movie: Movie) {
        movieStorage.saveMovieJsonToCsv(uri = uri, jsonNode = getMovieJson(movie = movie))
    }
}