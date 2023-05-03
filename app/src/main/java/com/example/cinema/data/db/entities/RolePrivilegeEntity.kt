package com.example.cinema.data.db.entities

import org.ktorm.entity.Entity

interface RolePrivilegeEntity : Entity<RolePrivilegeEntity> {

    companion object : Entity.Factory<RolePrivilegeEntity>()

    var id: Int
    var roleId: RoleEntity
    var privilegeId: PrivilegeEntity
}