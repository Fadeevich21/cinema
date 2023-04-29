package com.example.cinema.data.db.entities

import org.ktorm.entity.Entity

interface PlaceEntity : Entity<PlaceEntity> {
    companion object : Entity.Factory<PlaceEntity>()

    var id: Int
    var row: Int
    var seat: Int
}