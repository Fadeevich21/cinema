package com.example.cinema.domain.usecase

import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.repository.MovieRepository

class GetMovieByIdUseCase(private val repository: MovieRepository) {


    fun execute(id: Int): Movie {
        return repository.getMovieById(id)
    }
}