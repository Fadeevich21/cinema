package com.example.cinema.data.db.entities

import org.ktorm.entity.Entity

interface MovieEntity : Entity<MovieEntity> {

    companion object : Entity.Factory<MovieEntity>()

    var id: Int
    var name: String
    var description: String
    var duration: String
    var yearOfRelease: Int
    var ageRestriction: Int
    var trailerUrl: String?
}