package com.example.cinema.domain.usecase.model

import com.example.cinema.domain.usecase.movie.AddMovieUseCase
import com.example.cinema.domain.usecase.movie.BuyMovieUseCase
import com.example.cinema.domain.usecase.movie.CheckBoughtMovieByUserUseCase
import com.example.cinema.domain.usecase.movie.DeleteMovieUseCase
import com.example.cinema.domain.usecase.movie.FilterMoviesByNameUseCase
import com.example.cinema.domain.usecase.movie.GetAllMoviesUseCase
import com.example.cinema.domain.usecase.movie.GetBoughtMoviesByUserUseCase
import com.example.cinema.domain.usecase.movie.GetMovieByIdUseCase
import com.example.cinema.domain.usecase.movie.GetMovieJsonUseCase
import com.example.cinema.domain.usecase.movie.SaveMovieCsvUseCase
import com.example.cinema.domain.usecase.movie.SaveMovieJsonUseCase

data class MovieUseCases(
    val getMovieByIdUseCase: GetMovieByIdUseCase,
    val getAllMoviesUseCase: GetAllMoviesUseCase,
    val filterMoviesByNameUseCase: FilterMoviesByNameUseCase,
    val buyMovieUseCase: BuyMovieUseCase,
    val getBoughtMoviesByUserUseCase: GetBoughtMoviesByUserUseCase,
    val checkBoughtMovieByUserUseCase: CheckBoughtMovieByUserUseCase,
    val addMovieUseCase: AddMovieUseCase,
    val deleteMovieUseCase: DeleteMovieUseCase,
    val getMovieJsonUseCase: GetMovieJsonUseCase,
    val saveMovieJsonUseCase: SaveMovieJsonUseCase,
    val saveMovieCsvUseCase: SaveMovieCsvUseCase
)