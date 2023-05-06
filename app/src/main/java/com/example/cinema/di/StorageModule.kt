package com.example.cinema.di

import com.example.cinema.data.storage.ExternalMovieStorage
import com.example.cinema.data.storage.MovieStorage
import org.koin.dsl.module

val storageModule = module {

    single<MovieStorage> {
        ExternalMovieStorage(
            context = get()
        )
    }
}