package com.example.cinema.domain.usecase.model

import com.example.cinema.domain.usecase.genre.GetGenresByMovieIdUseCase

data class GenreUseCases(
    val getGenresByMovieIdUseCase: GetGenresByMovieIdUseCase
)