package com.example.cinema.domain.usecase.movie

import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.repository.MovieRepository

class GetMovieByIdUseCase(private val repository: MovieRepository) {

    fun execute(id: Int): Movie {
        return repository.getMovieById(id)
    }
}