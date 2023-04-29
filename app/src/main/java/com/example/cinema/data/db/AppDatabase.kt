package com.example.cinema.data.db

import com.example.cinema.data.db.daos.MoviesDao
import com.example.cinema.data.db.daos.PlacesDao
import com.example.cinema.data.db.daos.TicketsDao
import com.example.cinema.data.db.daos.UsersTicketsDao
import org.ktorm.database.Database

class AppDatabase {
    companion object {
        private var database: Database = Database.connect(
            url = "jdbc:postgresql://192.168.0.104:5432/postgres",
            driver = "org.postgresql.Driver",
            user = "fadeevich",
            password = "fadeevich"
        )

        var moviesDao = MoviesDao(database)
        var usersTicketsDao = UsersTicketsDao(database)
        var placesDao = PlacesDao(database)
        var ticketsDao = TicketsDao(database)
    }
}