package com.example.cinema.mapper

import com.example.cinema.data.db.entities.UserEntity
import com.example.cinema.domain.model.User
import com.example.cinema.mapper.utils.EntityMapper

class UserMapper(private val roleMapper: RoleMapper) : EntityMapper<UserEntity, User> {

    override fun mapFromEntity(entity: UserEntity): User {
        return User(
            username = entity.username,
            password = entity.password,
            role = roleMapper.mapFromEntity(entity.roleId)
        )
    }

    override fun mapToEntity(domainModel: User): UserEntity {
        return UserEntity {
            username = domainModel.username
            password = domainModel.password
            roleId = roleMapper.mapToEntity(domainModel.role)
        }
    }
}