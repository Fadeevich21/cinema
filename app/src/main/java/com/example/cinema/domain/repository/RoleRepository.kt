package com.example.cinema.domain.repository

import com.example.cinema.domain.model.Privilege
import com.example.cinema.domain.model.Role

interface RoleRepository {

    fun getRoleByName(name: String): Role?
    fun checkRoleHasPrivilege(role: Role, privilege: Privilege?): Boolean
}