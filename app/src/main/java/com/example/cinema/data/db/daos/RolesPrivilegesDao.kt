package com.example.cinema.data.db.daos

import com.example.cinema.data.db.entities.PrivilegeEntity
import com.example.cinema.data.db.entities.RoleEntity
import com.example.cinema.data.db.tables.RolesPrivilegesTable
import com.example.cinema.data.db.utils.Dao
import org.ktorm.database.Database
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.entity.any
import org.ktorm.entity.sequenceOf

class RolesPrivilegesDao(database: Database) : Dao(database = database) {

    private val rolesPrivileges = database.sequenceOf(RolesPrivilegesTable)

    fun checkRoleHasPrivilege(role: RoleEntity, privilege: PrivilegeEntity?): Boolean {
        if (privilege == null)
            return false

        return rolesPrivileges.any { (it.roleId eq role.id) and (it.privilegeId eq privilege.id) }
    }
}