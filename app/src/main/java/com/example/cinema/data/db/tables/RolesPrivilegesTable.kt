package com.example.cinema.data.db.tables

import com.example.cinema.data.db.entities.RolePrivilegeEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object RolesPrivilegesTable : Table<RolePrivilegeEntity>("roles_privileges") {

    val id = int("role_privilege_id").primaryKey().bindTo { it.id }
    val roleId = int("role_id").references(RolesTable) { it.roleId }
    val privilegeId = int("privilege_id").references(PrivilegesTable) { it.privilegeId }
}