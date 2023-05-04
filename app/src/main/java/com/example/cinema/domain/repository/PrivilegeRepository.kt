package com.example.cinema.domain.repository

import com.example.cinema.domain.model.Privilege

interface PrivilegeRepository {

    fun getPrivilegeByName(name: String): Privilege?
}