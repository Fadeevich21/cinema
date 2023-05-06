package com.example.cinema.data.db.daos

import com.example.cinema.data.db.entities.UserEntity
import com.example.cinema.data.db.tables.UsersTable
import com.example.cinema.data.db.utils.Dao
import org.ktorm.database.Database
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf

class UsersDao(database: Database) : Dao(database = database) {

    private val users = database.sequenceOf(UsersTable)

    fun findUserByUsernameAndPassword(username: String, password: String): UserEntity? {
        return users.find { (it.username eq username) and (it.password eq password) }
    }

    fun findUserByUsername(username: String): UserEntity? {
        return users.find { (it.username eq username) }
    }

    fun addUser(user: UserEntity) {
        users.add(user)
    }
}