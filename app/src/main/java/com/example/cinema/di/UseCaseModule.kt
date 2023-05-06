package com.example.cinema.di

import com.example.cinema.domain.usecase.file.GetFilenameUseCase
import com.example.cinema.domain.usecase.movie.AddMovieUseCase
import com.example.cinema.domain.usecase.movie.BuyMovieUseCase
import com.example.cinema.domain.usecase.movie.CheckBoughtMovieByUserUseCase
import com.example.cinema.domain.usecase.user.CheckUserHasPrivilegeUseCase
import com.example.cinema.domain.usecase.movie.DeleteMovieUseCase
import com.example.cinema.domain.usecase.movie.FilterMoviesByNameUseCase
import com.example.cinema.domain.usecase.movie.GetAllMoviesUseCase
import com.example.cinema.domain.usecase.movie.GetBoughtMoviesByUserUseCase
import com.example.cinema.domain.usecase.genre.GetGenresByMovieIdUseCase
import com.example.cinema.domain.usecase.model.FileUseCases
import com.example.cinema.domain.usecase.model.GenreUseCases
import com.example.cinema.domain.usecase.model.MovieUseCases
import com.example.cinema.domain.usecase.model.PrivilegeUseCases
import com.example.cinema.domain.usecase.model.RoleUseCases
import com.example.cinema.domain.usecase.model.UserUseCases
import com.example.cinema.domain.usecase.movie.GetMovieByIdUseCase
import com.example.cinema.domain.usecase.movie.GetMovieJsonUseCase
import com.example.cinema.domain.usecase.movie.SaveMovieCsvUseCase
import com.example.cinema.domain.usecase.movie.SaveMovieJsonUseCase
import com.example.cinema.domain.usecase.privilege.GetPrivilegeByNameUseCase
import com.example.cinema.domain.usecase.role.GetRoleByNameUseCase
import com.example.cinema.domain.usecase.user.LoginUserUseCase
import com.example.cinema.domain.usecase.user.RegisterUserUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single<FileUseCases> {
        FileUseCases(
            getFilenameUseCase = get()
        )
    }

    single<GenreUseCases> {
        GenreUseCases(
            getGenresByMovieIdUseCase = get()
        )
    }

    single<MovieUseCases> {
        MovieUseCases(
            getMovieByIdUseCase = get(),
            getAllMoviesUseCase = get(),
            filterMoviesByNameUseCase = get(),
            buyMovieUseCase = get(),
            getBoughtMoviesByUserUseCase = get(),
            checkBoughtMovieByUserUseCase = get(),
            addMovieUseCase = get(),
            deleteMovieUseCase = get(),
            getMovieJsonUseCase = get(),
            saveMovieJsonUseCase = get(),
            saveMovieCsvUseCase = get()
        )
    }

    single<PrivilegeUseCases> {
        PrivilegeUseCases(
            getPrivilegeByNameUseCase = get()
        )
    }

    single<RoleUseCases> {
        RoleUseCases(
            getRoleUseCases = get()
        )
    }

    single<UserUseCases> {
        UserUseCases(
            checkUserHasPrivilegeUseCase = get(),
            loginUserUseCase = get(),
            registerUserUseCase = get()
        )
    }


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

    factory<CheckUserHasPrivilegeUseCase> {
        CheckUserHasPrivilegeUseCase(repository = get())
    }

    factory<GetPrivilegeByNameUseCase> {
        GetPrivilegeByNameUseCase(repository = get())
    }

    factory<AddMovieUseCase> {
        AddMovieUseCase(repository = get())
    }

    factory<DeleteMovieUseCase> {
        DeleteMovieUseCase(repository = get())
    }

    factory<GetFilenameUseCase> {
        GetFilenameUseCase(repository = get())
    }

    factory<GetMovieJsonUseCase> {
        GetMovieJsonUseCase(repository = get())
    }

    factory<SaveMovieJsonUseCase> {
        SaveMovieJsonUseCase(repository = get())
    }

    factory<SaveMovieCsvUseCase> {
        SaveMovieCsvUseCase(repository = get())
    }
}