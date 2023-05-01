package com.example.cinema.domain.usecase

import com.example.cinema.domain.model.Ticket
import com.example.cinema.domain.repository.TicketRepository

class GetTicketByIdUseCase(private val repository: TicketRepository) {
    fun execute(id: Int): Ticket {
        return repository.getTicketById(id)
    }
}