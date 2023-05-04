package com.example.cinema.domain.usecase

import com.example.cinema.domain.model.Privilege
import com.example.cinema.domain.repository.PrivilegeRepository

class GetPrivilegeByNameUseCase(private val repository: PrivilegeRepository) {

    fun execute(name: String): Privilege? {
        return repository.getPrivilegeByName(name = name)
    }
}