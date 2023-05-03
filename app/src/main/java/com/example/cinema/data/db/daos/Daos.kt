package com.example.cinema.data.db.daos

data class Daos(
    val moviesDao: MoviesDao,
    val genresDao: GenresDao,
    val rolesDao: RolesDao,
    val usersDao: UsersDao,
    val privilegesDao: PrivilegeDao,
    val moviesGenresDao: MoviesGenresDao,
    val usersMoviesDao: UsersMoviesDao,
    val rolesPrivilegesDao: RolesPrivilegesDao
)