package com.example.cinema.domain.usecase

import com.example.cinema.domain.model.MoviePoster
import com.example.cinema.domain.repository.MoviePosterRepository

class GetAllMoviePostersUseCase(private val repository: MoviePosterRepository) {
    fun execute(): List<MoviePoster> {
        return repository.getAllMoviePosters()
    }
}