package com.example.cinema.data.db.entities

import org.ktorm.entity.Entity

interface PrivilegeEntity : Entity<PrivilegeEntity> {

    companion object : Entity.Factory<PrivilegeEntity>()

    var id: Int
    var name: String
}