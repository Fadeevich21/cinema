package com.example.cinema.domain.usecase

import com.example.cinema.domain.model.Ticket
import com.example.cinema.domain.repository.TicketRepository

class GetTicketsByUserIdUseCase(private val repository: TicketRepository) {
    fun execute(id: Int): List<Ticket> {
        return repository.getTicketsByUserId(id)
    }
}