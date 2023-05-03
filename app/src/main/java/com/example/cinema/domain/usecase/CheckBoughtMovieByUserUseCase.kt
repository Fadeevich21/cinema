package com.example.cinema.domain.usecase

import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.model.User
import com.example.cinema.domain.repository.MovieRepository

class CheckBoughtMovieByUserUseCase(private val repository: MovieRepository) {

    fun execute(user: User, movie: Movie): Boolean {
        return repository.checkBoughtMovieByUser(user = user, movie = movie)
    }
}