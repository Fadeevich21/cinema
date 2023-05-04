package com.example.cinema.domain.usecase

import com.example.cinema.domain.model.Privilege
import com.example.cinema.domain.model.User
import com.example.cinema.domain.repository.RoleRepository

class CheckUserHasPrivilegeUseCase(private val repository: RoleRepository) {

    fun execute(user: User, privilege: Privilege?): Boolean {
        return repository.checkRoleHasPrivilege(user.role, privilege)
    }
}