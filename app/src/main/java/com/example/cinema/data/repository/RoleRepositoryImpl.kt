package com.example.cinema.data.repository

import com.example.cinema.data.db.AppDatabase
import com.example.cinema.domain.model.Privilege
import com.example.cinema.domain.model.Role
import com.example.cinema.domain.repository.RoleRepository
import com.example.cinema.mapper.PrivilegeMapper
import com.example.cinema.mapper.RoleMapper

class RoleRepositoryImpl(
    private val roleMapper: RoleMapper,
    private val privilegeMapper: PrivilegeMapper
) : RoleRepository {

    override fun getRoleByName(name: String): Role? {
        val roleEntity = AppDatabase.daos.rolesDao.getRoleByName(name)

        return roleEntity?.let { roleMapper.mapFromEntity(it) }
    }

    override fun checkRoleHasPrivilege(role: Role, privilege: Privilege?): Boolean {
        val roleEntity = roleMapper.mapToEntity(role)
        val privilegeEntity = privilege?.let { privilegeMapper.mapToEntity(it) }

        return AppDatabase.daos.rolesPrivilegesDao.checkRoleHasPrivilege(
            role = roleEntity,
            privilege = privilegeEntity
        )
    }
}