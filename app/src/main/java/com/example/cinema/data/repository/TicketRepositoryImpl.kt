package com.example.cinema.data.repository

import android.widget.Toast
import com.example.cinema.data.db.AppDatabase
import com.example.cinema.data.db.entities.UserTicketEntity
import com.example.cinema.data.toDomain
import com.example.cinema.domain.model.Ticket
import com.example.cinema.domain.repository.TicketRepository
import java.util.Locale

class TicketRepositoryImpl : TicketRepository {
    override fun getAllTickets(): List<Ticket> {
        return AppDatabase.ticketsDao.getAllTickets().map { it.toDomain() }
    }

    override fun getTicketById(id: Int): Ticket {
        return AppDatabase.ticketsDao.getTicketById(id).toDomain()
    }

    override fun getTicketsByUserId(id: Int): List<Ticket> {
        return AppDatabase.usersTicketsDao.getTicketsByUserId(id).map { it.ticketId.toDomain() }
    }

    override fun searchTicketsByName(name: String): List<Ticket> {
        val tickets = getAllTickets()
        if (tickets.isEmpty()) return tickets
        val filteredTickets = mutableListOf<Ticket>()
        for (ticket in tickets)
            if (ticket.movie.name.lowercase().contains(name.lowercase(Locale.getDefault())))
                filteredTickets.add(ticket)

        return filteredTickets
    }

    override fun buyTicket(userId: Int, ticketId: Int): Boolean {
        val userTicketEntity = UserTicketEntity {
            this.ticketId = AppDatabase.ticketsDao.getTicketById(ticketId)
            this.userId = userId
        }

        var isTicketBuy = false
        if (!AppDatabase.usersTicketsDao.checkTicketExist(userTicketEntity.ticketId)) {
            isTicketBuy = true
            AppDatabase.usersTicketsDao.addUserTicketEntity(userTicketEntity)
        }

        return isTicketBuy
    }
}