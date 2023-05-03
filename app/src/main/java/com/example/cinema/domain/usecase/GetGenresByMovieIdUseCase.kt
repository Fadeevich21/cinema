package com.example.cinema.domain.usecase

import com.example.cinema.domain.model.Genre
import com.example.cinema.domain.repository.GenreRepository

class GetGenresByMovieIdUseCase(private val repository: GenreRepository) {

    fun execute(movieId: Int): List<Genre> {
        return repository.getGenresByMovieId(movieId)
    }
}