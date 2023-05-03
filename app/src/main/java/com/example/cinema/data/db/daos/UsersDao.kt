package com.example.cinema.data.db.daos

import com.example.cinema.data.db.tables.UsersTable
import com.example.cinema.data.db.utils.Dao
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf

class UsersDao(database: Database) : Dao(database = database) {

    val users = database.sequenceOf(UsersTable)
}