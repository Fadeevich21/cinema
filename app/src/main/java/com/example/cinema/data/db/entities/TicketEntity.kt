package com.example.cinema.data.db.entities

import org.ktorm.entity.Entity

interface TicketEntity : Entity<TicketEntity> {
    companion object : Entity.Factory<TicketEntity>()

    var id: Int
    var movie: MovieEntity
    var place: PlaceEntity
}