package com.example.cinema.data.db.daos

import com.example.cinema.data.db.entities.PrivilegeEntity
import com.example.cinema.data.db.tables.PrivilegesTable
import com.example.cinema.data.db.utils.Dao
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf

class PrivilegeDao(database: Database) : Dao(database = database) {

    private val privileges = database.sequenceOf(PrivilegesTable)

    fun getPrivilegeByName(name: String): PrivilegeEntity? {
        return privileges.find { it.name eq name }
    }
}