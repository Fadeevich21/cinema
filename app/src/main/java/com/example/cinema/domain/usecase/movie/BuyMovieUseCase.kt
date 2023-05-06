package com.example.cinema.domain.usecase.movie

import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.model.User
import com.example.cinema.domain.repository.MovieRepository

class BuyMovieUseCase(private val repository: MovieRepository) {

    fun execute(user: User, movie: Movie): Boolean {
        return repository.buyMovie(user = user, movie = movie)
    }
}