package com.example.cinema.data.db.tables

import com.example.cinema.data.db.entities.RoleEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

object RolesTable: Table<RoleEntity>("roles")  {

    val id = int("role_id").primaryKey().bindTo { it.id }
    val name = text("name").bindTo { it.name }
}