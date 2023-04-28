package com.example.cinema.database.entities

import org.ktorm.entity.Entity
import org.ktorm.schema.ColumnDeclaring

interface UserTicketEntity : Entity<UserTicketEntity>, ColumnDeclaring<Boolean> {
    companion object : Entity.Factory<UserTicketEntity>()

    var ticketId: TicketEntity
    var userId: Int
}