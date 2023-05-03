package com.example.cinema.data.db.entities

import org.ktorm.entity.Entity

interface RoleEntity : Entity<RoleEntity> {

    companion object : Entity.Factory<RoleEntity>()

    var id: Int
    var name: String
}