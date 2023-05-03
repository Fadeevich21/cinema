package com.example.cinema.di

import com.example.cinema.data.db.daos.Daos
import org.koin.dsl.module
import org.ktorm.database.Database

val databaseModule = module {

    single<Database> {
        Database.connect(
            url = "jdbc:postgresql://192.168.0.104:5432/postgres",
            driver = "org.postgresql.Driver",
            user = "fadeevich",
            password = "fadeevich"
        )
    }

    factory<Daos> {
        Daos(
            moviesDao = get(),
            genresDao = get(),
            usersDao = get(),
            rolesDao = get(),
            privilegesDao = get(),
            moviesGenresDao = get(),
            usersMoviesDao = get(),
            rolesPrivilegesDao = get()
        )
    }
}