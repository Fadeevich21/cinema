package com.example.cinema.domain.usecase

import com.example.cinema.domain.repository.TicketRepository

class BuyTicketUseCase(private val repository: TicketRepository) {
    fun execute(userId: Int, ticketId: Int): Boolean {
        return repository.buyTicket(userId, ticketId)
    }
}