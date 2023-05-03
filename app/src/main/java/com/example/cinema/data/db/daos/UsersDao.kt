package com.example.cinema.data.db.daos

import com.example.cinema.data.db.tables.UsersTable
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf

class UsersDao(database: Database) : Dao(database = database) {

    val users = database.sequenceOf(UsersTable)
}