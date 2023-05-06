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
            movieUseCases = get(),
            userUseCases = get(),
            privilegeUseCases = get()
        )
    }

    viewModel<ShopViewModel> {
        ShopViewModel(
            movieUseCases = get()
        )
    }

    viewModel<LoginViewModel> {
        LoginViewModel(
            userUseCases = get()
        )
    }

    viewModel<MovieDetailViewModel> {
        MovieDetailViewModel(
            movieUseCases = get(),
            genreUseCases = get(),
            privilegeUseCases = get(),
            userUseCases = get(),
            fileUseCases = get()
        )
    }

    viewModel<AddMovieViewModel> {
        AddMovieViewModel(
            movieUseCases = get()
        )
    }
}