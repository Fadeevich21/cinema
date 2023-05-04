package com.example.cinema.data.repository

import com.example.cinema.data.db.AppDatabase
import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.model.User
import com.example.cinema.domain.repository.MovieRepository
import com.example.cinema.mapper.MovieMapper
import com.example.cinema.mapper.UserMapper
import java.util.Locale

class MovieRepositoryImpl(
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
        for (movie in movies)
            if (movie.name.lowercase().contains(name.lowercase(Locale.getDefault())))
                filteredMovies.add(movie)

        return filteredMovies
    }

    override fun buyMovie(user: User, movie: Movie): Boolean {
        return AppDatabase.daos.usersMoviesDao.addRecord(
            user = userMapper.mapToEntity(user),
            movie = movieMapper.mapToEntity(movie)
        )
    }

    override fun getBoughtMoviesByUser(user: User): List<Movie> {
        val userEntity = userMapper.mapToEntity(user)

        return movieMapper.fromEntityList(
            AppDatabase.daos.usersMoviesDao.getMoviesByUser(userEntity)
        )
    }

    override fun checkBoughtMovieByUser(user: User, movie: Movie): Boolean {
        val userEntity = userMapper.mapToEntity(user)
        val movieEntity = movieMapper.mapToEntity(movie)

        return AppDatabase.daos.usersMoviesDao.checkMovieByUser(
            user = userEntity,
            movie = movieEntity
        )
    }

    override fun addMovie(movie: Movie): Boolean {
        if (movie.id == null)
            movie.id = AppDatabase.daos.moviesDao.getCountMovies() + 1
        val movieEntity = movieMapper.mapToEntity(movie)
        return AppDatabase.daos.moviesDao.addMovie(movie = movieEntity)
    }

    override fun deleteMovie(movie: Movie): Boolean {
        val movieEntity = movieMapper.mapToEntity(movie)
        return AppDatabase.daos.moviesDao.deleteMovie(movie = movieEntity)
    }
}