package com.example.cinema.di

import com.example.cinema.ui.activies.addMovie.AddMovieViewModel
import com.example.cinema.ui.fragments.home.HomeViewModel
import com.example.cinema.ui.activies.login.LoginViewModel
import com.example.cinema.ui.activies.movieDetail.MovieDetailViewModel
import com.example.cinema.ui.fragments.shop.ShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<HomeViewModel> {
        HomeViewModel(
            getAllMoviesUseCase = get(),
            filterMoviesByNameUseCase = get(),
            checkUserHasPrivilegeUseCase = get(),
            getPrivilegeByNameUseCase = get()
        )
    }

    viewModel<ShopViewModel> {
        ShopViewModel(
            getAllMoviesUseCase = get(),
            getBoughtMoviesByUserUseCase = get()
        )
    }

    viewModel<LoginViewModel> {
        LoginViewModel(
            loginUserUseCase = get(),
            registerUserUseCase = get()
        )
    }

    viewModel<MovieDetailViewModel> {
        MovieDetailViewModel(
            getMovieByIdUseCase = get(),
            getGenresByMovieIdUseCase = get(),
            buyMovieUseCase = get(),
            checkBoughtMovieByUserUseCase = get(),
            getPrivilegeByNameUseCase = get(),
            checkUserHasPrivilegeUseCase = get(),
            deleteMovieUseCase = get()
        )
    }

    viewModel<AddMovieViewModel> {
        AddMovieViewModel(
            addMovieUseCase = get()
        )
    }
}