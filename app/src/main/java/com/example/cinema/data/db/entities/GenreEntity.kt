package com.example.cinema.data.db.entities

import org.ktorm.entity.Entity

interface GenreEntity : Entity<GenreEntity> {

    companion object : Entity.Factory<GenreEntity>()

    var id: Int
    var name: String
}