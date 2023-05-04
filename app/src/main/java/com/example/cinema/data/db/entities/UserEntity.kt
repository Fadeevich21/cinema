package com.example.cinema.data.db.entities

import org.ktorm.entity.Entity

interface UserEntity : Entity<UserEntity> {

    companion object : Entity.Factory<UserEntity>()

    var username: String
    var password: String
    var roleId: RoleEntity
}