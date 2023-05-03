package com.example.cinema.data.db.tables

import com.example.cinema.data.db.entities.PrivilegeEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

object PrivilegesTable : Table<PrivilegeEntity>("privileges") {

    val id = int("privilege_id").primaryKey().bindTo { it.id }
    val name = text("name").bindTo { it.name }
}