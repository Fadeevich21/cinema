package com.example.cinema.domain.usecase.movie

import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.model.User
import com.example.cinema.domain.repository.MovieRepository

class GetBoughtMoviesByUserUseCase(private var repository: MovieRepository) {

    fun execute(user: User): List<Movie> {
        return repository.getBoughtMoviesByUser(user = user)
    }
}