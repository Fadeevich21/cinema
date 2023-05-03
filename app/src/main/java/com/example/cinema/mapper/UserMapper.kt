package com.example.cinema.mapper

import com.example.cinema.data.db.entities.UserEntity
import com.example.cinema.domain.model.User
import com.example.cinema.utils.EntityMapper

class UserMapper(private val roleMapper: RoleMapper) : EntityMapper<UserEntity, User> {

    override fun mapFromEntity(entity: UserEntity): User {
        return User(
            id = entity.id,
            name = entity.name,
            login = entity.login,
            password = entity.password,
            role = roleMapper.mapFromEntity(entity.roleId)
        )
    }

    override fun mapToEntity(domainModel: User): UserEntity {
        return UserEntity {
            id = domainModel.id
            name = domainModel.name
            login = domainModel.login
            password = domainModel.password
            roleId = roleMapper.mapToEntity(domainModel.role)
        }
    }
}