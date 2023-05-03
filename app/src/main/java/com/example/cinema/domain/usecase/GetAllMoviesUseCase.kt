package com.example.cinema.domain.usecase

import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.repository.MovieRepository

class GetAllMoviesUseCase(private val repository: MovieRepository) {

    fun execute(): List<Movie> {
        return repository.getAllMovies()
    }
}