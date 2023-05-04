package com.example.cinema.domain.usecase

import com.example.cinema.domain.model.Role
import com.example.cinema.domain.repository.RoleRepository

class GetRoleByNameUseCase(private val repository: RoleRepository) {

    fun execute(name: String): Role? {
        return repository.getRoleByName(name)
    }
}