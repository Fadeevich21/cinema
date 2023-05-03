package com.example.cinema.di

import com.example.cinema.data.repository.GenreRepositoryImpl
import com.example.cinema.data.repository.MovieRepositoryImpl
import com.example.cinema.domain.repository.GenreRepository
import com.example.cinema.domain.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MovieRepository> {
        MovieRepositoryImpl(
            movieMapper = get(),
            userMapper = get()
        )
    }

    single<GenreRepository> {
        GenreRepositoryImpl(
            genreMapper = get()
        )
    }
}