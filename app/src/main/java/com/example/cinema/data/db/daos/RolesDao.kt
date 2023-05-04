package com.example.cinema.data.db.daos

import com.example.cinema.data.db.entities.RoleEntity
import com.example.cinema.data.db.tables.RolesTable
import com.example.cinema.data.db.utils.Dao
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf

class RolesDao(database: Database) : Dao(database = database) {

    private val roles = database.sequenceOf(RolesTable)

    fun getRoleByName(name: String): RoleEntity? {
        return roles.find { it.name eq name }
    }
}