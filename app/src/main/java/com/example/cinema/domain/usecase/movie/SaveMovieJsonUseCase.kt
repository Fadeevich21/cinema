package com.example.cinema.domain.usecase.movie

import android.net.Uri
import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.repository.MovieRepository

class SaveMovieJsonUseCase(private val repository: MovieRepository) {

    fun execute(uri: Uri?, movie: Movie) {
        repository.saveMovieJson(uri = uri, movie = movie)
    }
}