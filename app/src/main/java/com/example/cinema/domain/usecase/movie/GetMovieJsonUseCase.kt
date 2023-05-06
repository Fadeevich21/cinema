package com.example.cinema.domain.usecase.movie

import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.repository.MovieRepository
import com.fasterxml.jackson.databind.JsonNode

class GetMovieJsonUseCase(private val repository: MovieRepository) {

    fun execute(movie: Movie): JsonNode {
        return repository.getMovieJson(movie = movie)
    }
}