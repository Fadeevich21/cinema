package com.example.cinema.data.db.daos

import com.example.cinema.data.db.tables.RolesPrivilegesTable
import com.example.cinema.data.db.utils.Dao
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf

class RolesPrivilegesDao(database: Database): Dao(database = database) {

    private val rolesPrivileges = database.sequenceOf(RolesPrivilegesTable)
}