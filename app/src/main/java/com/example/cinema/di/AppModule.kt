package com.example.cinema.di

import com.example.cinema.ui.viewModel.HomeViewModel
import com.example.cinema.ui.viewModel.MovieDetailViewModel
import com.example.cinema.ui.viewModel.ShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<HomeViewModel> {
        HomeViewModel(
            getAllMoviesUseCase = get(),
            filterMoviesByNameUseCase = get()
        )
    }

    viewModel<ShopViewModel> {
        ShopViewModel(
            getAllMoviesUseCase = get(),
            getBoughtMoviesByUserUseCase = get()
        )
    }

    viewModel<MovieDetailViewModel> {
        MovieDetailViewModel(
            getMovieByIdUseCase = get(),
            getGenresByMovieIdUseCase = get(),
            buyMovieUseCase = get(),
            checkBoughtMovieByUserUseCase = get()
        )
    }
}