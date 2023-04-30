package com.example.cinema.data.repository

import com.example.cinema.data.db.AppDatabase
import com.example.cinema.data.toDomain
import com.example.cinema.domain.model.Ticket
import com.example.cinema.domain.repository.TicketRepository

class TicketRepositoryImpl : TicketRepository {
    override fun getAllTickets(): List<Ticket> {
        return AppDatabase.ticketsDao.getAllTickets().map { it.toDomain() }
    }

    override fun getTicketsByUserId(id: Int): List<Ticket> {
        return AppDatabase.usersTicketsDao.getTicketsByUserId(id).map { it.ticketId.toDomain() }
    }
}