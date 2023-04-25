package com.example.cinema.database.tables

import com.example.cinema.database.entities.UserTicketEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object UserTicketTable : Table<UserTicketEntity>("users_tickets") {
    val ticketId = int("ticket_id").primaryKey().references(MovieTable) { it.ticketId }
    val userId = int("user_id").bindTo { it.userId }
}