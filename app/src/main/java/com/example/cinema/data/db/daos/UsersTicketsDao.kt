package com.example.cinema.data.db.daos

import com.example.cinema.data.db.entities.TicketEntity
import com.example.cinema.data.db.entities.UserTicketEntity
import com.example.cinema.data.db.tables.UsersTicketsTable
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.any
import org.ktorm.entity.drop
import org.ktorm.entity.filter
import org.ktorm.entity.removeIf
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class UsersTicketsDao(database: Database) : Dao(database) {
    private val usersTickets get() = database.sequenceOf(UsersTicketsTable)

    fun getTicketsByUserId(userId: Int): List<UserTicketEntity> {
        return usersTickets.filter { it.userId eq userId }.toList()
    }

    fun addUserTicketEntity(userTicketEntity: UserTicketEntity) {
        usersTickets.add(userTicketEntity)
    }

    fun checkTicketExist(ticket: TicketEntity): Boolean {
        return usersTickets.any { it.ticketId eq ticket.id }
    }

    fun removeUserTicketById(id: Int) {
        usersTickets.removeIf { it.ticketId eq id }
    }
}