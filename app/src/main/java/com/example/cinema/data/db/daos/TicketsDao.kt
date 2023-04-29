package com.example.cinema.data.db.daos

import com.example.cinema.data.db.entities.TicketEntity
import com.example.cinema.data.db.tables.TicketsTable
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class TicketsDao(database: Database) : Dao(database) {
    private val tickets get() = database.sequenceOf(TicketsTable)

    fun getAllTickets(): List<TicketEntity> {
        return tickets.toList()
    }

    fun getTicketById(id: Int): TicketEntity {
        return tickets.filter { it.id eq id }.toList().first()
    }


}