package com.example.cinema.mapper

import com.example.cinema.data.db.entities.PrivilegeEntity
import com.example.cinema.domain.model.Privilege
import com.example.cinema.utils.EntityMapper

class PrivilegeMapper : EntityMapper<PrivilegeEntity, Privilege> {

    override fun mapFromEntity(entity: PrivilegeEntity): Privilege {
        return Privilege(
            id = entity.id,
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: Privilege): PrivilegeEntity {
        return PrivilegeEntity {
            id = domainModel.id
            name = domainModel.name
        }
    }

}