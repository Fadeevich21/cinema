package com.example.cinema.data.repository

import com.example.cinema.data.db.AppDatabase
import com.example.cinema.domain.model.Privilege
import com.example.cinema.domain.repository.PrivilegeRepository
import com.example.cinema.mapper.PrivilegeMapper

class PrivilegeRepositoryImpl(private val privilegeMapper: PrivilegeMapper) : PrivilegeRepository {

    override fun getPrivilegeByName(name: String): Privilege? {
        val privilegeEntity = AppDatabase.daos.privilegesDao.getPrivilegeByName(name = name)

        return privilegeEntity?.let { privilegeMapper.mapFromEntity(it) }
    }
}