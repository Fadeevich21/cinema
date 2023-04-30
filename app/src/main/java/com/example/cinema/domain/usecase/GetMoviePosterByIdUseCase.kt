package com.example.cinema.domain.usecase

import com.example.cinema.domain.model.MoviePoster
import com.example.cinema.domain.repository.MoviePosterRepository

class GetMoviePosterByIdUseCase(private val repository: MoviePosterRepository) {
    fun execute(id: Int): MoviePoster {
        return repository.getMoviePosterById(id)
    }
}