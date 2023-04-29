package com.example.cinema.data.db.tables

import com.example.cinema.data.db.entities.UserTicketEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object UsersTicketsTable : Table<UserTicketEntity>("users_tickets") {
    val ticketId = int("ticket_id").primaryKey().references(TicketsTable) { it.ticketId }
    val userId = int("user_id").bindTo { it.userId }
}