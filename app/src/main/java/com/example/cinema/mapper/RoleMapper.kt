package com.example.cinema.mapper

import com.example.cinema.data.db.entities.RoleEntity
import com.example.cinema.domain.model.Role
import com.example.cinema.mapper.utils.EntityMapper

class RoleMapper : EntityMapper<RoleEntity, Role> {

    override fun mapFromEntity(entity: RoleEntity): Role {
        return Role(
            id = entity.id,
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: Role): RoleEntity {
        return RoleEntity {
            id = domainModel.id
            name = domainModel.name
        }
    }
}