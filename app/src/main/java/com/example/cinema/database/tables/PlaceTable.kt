package com.example.cinema.database.tables

import com.example.cinema.database.entities.PlaceEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object PlaceTable : Table<PlaceEntity>("places") {
    val id = int("place_id").primaryKey().bindTo { it.id }
    val row = int("row").bindTo { it.row }
    val seat = int("seat").bindTo { it.seat }
}
