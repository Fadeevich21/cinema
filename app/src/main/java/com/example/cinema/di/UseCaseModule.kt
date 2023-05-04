package com.example.cinema.di

import com.example.cinema.domain.usecase.BuyMovieUseCase
import com.example.cinema.domain.usecase.CheckBoughtMovieByUserUseCase
import com.example.cinema.domain.usecase.FilterMoviesByNameUseCase
import com.example.cinema.domain.usecase.GetAllMoviesUseCase
import com.example.cinema.domain.usecase.GetBoughtMoviesByUserUseCase
import com.example.cinema.domain.usecase.GetGenresByMovieIdUseCase
import com.example.cinema.domain.usecase.GetMovieByIdUseCase
import com.example.cinema.domain.usecase.GetRoleByNameUseCase
import com.example.cinema.domain.usecase.LoginUserUseCase
import com.example.cinema.domain.usecase.RegisterUserUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory<GetAllMoviesUseCase> {
        GetAllMoviesUseCase(repository = get())
    }

    factory<GetMovieByIdUseCase> {
        GetMovieByIdUseCase(repository = get())
    }

    factory<FilterMoviesByNameUseCase> {
        FilterMoviesByNameUseCase(repository = get())
    }

    factory<GetGenresByMovieIdUseCase> {
        GetGenresByMovieIdUseCase(repository = get())
    }

    factory<BuyMovieUseCase> {
        BuyMovieUseCase(repository = get())
    }

    factory<GetBoughtMoviesByUserUseCase> {
        GetBoughtMoviesByUserUseCase(repository = get())
    }

    factory<CheckBoughtMovieByUserUseCase> {
        CheckBoughtMovieByUserUseCase(repository = get())
    }

    factory<GetRoleByNameUseCase> {
        GetRoleByNameUseCase(repository = get())
    }

    factory<LoginUserUseCase> {
        LoginUserUseCase(repository = get())
    }

    factory<RegisterUserUseCase> {
        RegisterUserUseCase(repository = get())
    }
}