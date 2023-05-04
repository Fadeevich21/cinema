package com.example.cinema.domain.usecase

import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.repository.MovieRepository

class AddMovieUseCase(private val repository: MovieRepository) {

    fun execute(movie: Movie): Boolean {
        return repository.addMovie(movie = movie)
    }
}