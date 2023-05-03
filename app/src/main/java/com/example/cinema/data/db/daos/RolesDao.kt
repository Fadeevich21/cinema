package com.example.cinema.data.db.daos

import com.example.cinema.data.db.tables.RolesTable
import com.example.cinema.data.db.utils.Dao
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf

class RolesDao(database: Database) : Dao(database = database) {

    private val roles = database.sequenceOf(RolesTable)
}