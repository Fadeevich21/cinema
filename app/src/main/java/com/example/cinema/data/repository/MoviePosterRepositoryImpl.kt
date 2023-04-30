package com.example.cinema.data.repository

import com.example.cinema.data.db.AppDatabase
import com.example.cinema.data.toDomain
import com.example.cinema.domain.model.MoviePoster
import com.example.cinema.domain.repository.MoviePosterRepository

class MoviePosterRepositoryImpl : MoviePosterRepository {
    override fun getMoviePosterById(id: Int): MoviePoster {
        return AppDatabase.moviesDao.getMovieById(id).toDomain()
    }

    override fun getAllMoviePosters(): List<MoviePoster> {
        return AppDatabase.moviesDao.getAllMovies().map { it.toDomain() }
    }
}