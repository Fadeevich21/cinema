package com.example.cinema.database.entities

import org.ktorm.entity.Entity


interface UserTicketEntity: Entity<UserTicketEntity> {
    companion object: Entity.Factory<UserTicketEntity>()

    var ticketId: MovieEntity
    var userId: Int

    fun set(ticket: MovieEntity, userId: Int) {
        this.ticketId = ticket
        this.userId = userId
    }
}