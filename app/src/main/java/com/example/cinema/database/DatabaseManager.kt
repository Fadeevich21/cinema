package com.example.cinema.database

import com.example.cinema.database.entities.MovieEntity
import com.example.cinema.database.entities.UserTicketEntity
import com.example.cinema.database.tables.MovieTable
import com.example.cinema.database.tables.UserTicketTable
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.filter
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseManager {
    private val movies get() = database.sequenceOf(MovieTable)
    private val usersTickets get() = database.sequenceOf(UserTicketTable)

    private var database: Database = Database.connect(
        url = "jdbc:postgresql://192.168.0.104:5432/postgres",
        driver = "org.postgresql.Driver",
        user = "fadeevich",
        password = "fadeevich"
    )

    fun getAllMovies(): List<MovieEntity> {
        return movies.toList()
    }

    fun getMovieById(id: Int): MovieEntity {
        return movies.filter { it.id eq id }.toList()[0]
    }

    fun getTicketsByUserId(userId: Int): List<UserTicketEntity> {
        return usersTickets.filter { it.userId eq userId }.toList()
    }

    fun insertUserTicketEntity(userTicketEntity: UserTicketEntity) {
        usersTickets.add(userTicketEntity)
    }
}