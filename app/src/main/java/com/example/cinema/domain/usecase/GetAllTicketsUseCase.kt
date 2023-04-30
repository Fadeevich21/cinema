package com.example.cinema.domain.usecase

import com.example.cinema.domain.model.Ticket
import com.example.cinema.domain.repository.TicketRepository

class GetAllTicketsUseCase(private val repository: TicketRepository) {
    fun execute(): List<Ticket> {
        return repository.getAllTickets()
    }
}