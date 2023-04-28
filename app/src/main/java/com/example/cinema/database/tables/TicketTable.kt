package com.example.cinema.database.tables

import com.example.cinema.database.entities.TicketEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object TicketTable : Table<TicketEntity>("tickets") {
    val id = int("ticket_id").primaryKey().bindTo { it.id }
    val movieId = int("movie_id").references(MovieTable) { it.movie }
    val placeId = int("place_id").references(PlaceTable) { it.place }
}