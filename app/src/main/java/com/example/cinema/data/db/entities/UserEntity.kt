package com.example.cinema.data.db.entities

import org.ktorm.entity.Entity

interface UserEntity : Entity<UserEntity> {

    companion object : Entity.Factory<UserEntity>()

    // TODO: сделать login первичным ключом. Тогда несколько пользователей не смогут быть с одним логином, но могут быть с одним паролем
    var id: Int
    var name: String
    var login: String
    var password: String
    var roleId: RoleEntity
}