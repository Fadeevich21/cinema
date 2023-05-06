package com.example.cinema.domain.usecase.movie

import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.repository.MovieRepository

class
FilterMoviesByNameUseCase(private val repository: MovieRepository) {

    fun execute(movies: List<Movie>, name: String): List<Movie> {
        return repository.filterMoviesByName(movies, name)
    }
}