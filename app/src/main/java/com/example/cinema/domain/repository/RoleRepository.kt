package com.example.cinema.domain.repository

import com.example.cinema.domain.model.Role

interface RoleRepository {

    fun getRoleByName(name: String): Role
}