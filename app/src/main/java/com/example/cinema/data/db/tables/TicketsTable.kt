package com.example.cinema.data.db.tables

import com.example.cinema.data.db.entities.TicketEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object TicketsTable : Table<TicketEntity>("tickets") {
    val id = int("ticket_id").primaryKey().bindTo { it.id }
    val movieId = int("movie_id").references(MoviesTable) { it.movie }
    val placeId = int("place_id").references(PlacesTable) { it.place }
}