package com.example.cinema.di

import com.example.cinema.data.repository.GenreRepositoryImpl
import com.example.cinema.data.repository.MovieRepositoryImpl
import com.example.cinema.data.repository.UserRepositoryImpl
import com.example.cinema.domain.repository.GenreRepository
import com.example.cinema.domain.repository.MovieRepository
import com.example.cinema.domain.repository.UserRepository
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

    single<UserRepository> {
        UserRepositoryImpl(
            userMapper = get(),
            roleMapper = get()
        )
    }
}