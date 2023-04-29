package com.example.cinema.data.db.entities

import org.ktorm.entity.Entity


interface MovieEntity: Entity<MovieEntity> {
    companion object: Entity.Factory<MovieEntity>()

    val id: Int
    val name: String
    val description: String
    val duration: String
}