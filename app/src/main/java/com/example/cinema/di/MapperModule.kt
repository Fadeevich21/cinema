package com.example.cinema.di

import com.example.cinema.mapper.GenreMapper
import com.example.cinema.mapper.MovieMapper
import com.example.cinema.mapper.PrivilegeMapper
import com.example.cinema.mapper.RoleMapper
import com.example.cinema.mapper.UserMapper
import org.koin.dsl.module

val mapperModule = module {

    single<GenreMapper> {
        GenreMapper()
    }

    single<MovieMapper> {
        MovieMapper()
    }

    single<RoleMapper> {
        RoleMapper()
    }

    single<UserMapper> {
        UserMapper(roleMapper = get())
    }

    single<PrivilegeMapper> {
        PrivilegeMapper()
    }
}