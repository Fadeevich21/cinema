package com.example.cinema.di

import com.example.cinema.data.db.daos.GenresDao
import com.example.cinema.data.db.daos.MoviesDao
import com.example.cinema.data.db.daos.MoviesGenresDao
import com.example.cinema.data.db.daos.PrivilegeDao
import com.example.cinema.data.db.daos.RolesDao
import com.example.cinema.data.db.daos.RolesPrivilegesDao
import com.example.cinema.data.db.daos.UsersDao
import com.example.cinema.data.db.daos.UsersMoviesDao
import org.koin.dsl.module

val daoModule = module {

    factory<MoviesDao> {
        MoviesDao(database = get())
    }

    factory<GenresDao> {
        GenresDao(database = get())
    }

    factory<RolesDao> {
        RolesDao(database = get())
    }

    factory<UsersDao> {
        UsersDao(database = get())
    }

    factory<MoviesGenresDao> {
        MoviesGenresDao(database = get())
    }

    factory<UsersMoviesDao> {
        UsersMoviesDao(database = get())
    }

    factory<PrivilegeDao> {
        PrivilegeDao(database = get())
    }

    factory<RolesPrivilegesDao> {
        RolesPrivilegesDao(database = get())
    }
}