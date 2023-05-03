package com.example.cinema.data.db.entities

import org.ktorm.entity.Entity

interface UserMovieEntity : Entity<UserMovieEntity> {

    companion object: Entity.Factory<UserMovieEntity>()

    var id: Int
    var userId: UserEntity
    var movieId: MovieEntity
}