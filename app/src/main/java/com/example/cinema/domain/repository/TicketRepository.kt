package com.example.cinema.domain.repository

import com.example.cinema.domain.model.Ticket

interface TicketRepository {
    fun getAllTickets(): List<Ticket>
    fun getTicketsByUserId(id: Int): List<Ticket>
}