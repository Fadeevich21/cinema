package com.example.cinema.data.db.tables

import com.example.cinema.data.db.entities.UserEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

object UsersTable : Table<UserEntity>("users") {

    val username = text("username").primaryKey().bindTo { it.username }
    val password = text("password").bindTo { it.password }
    val roleId = int("role_id").references(RolesTable) { it.roleId }
}