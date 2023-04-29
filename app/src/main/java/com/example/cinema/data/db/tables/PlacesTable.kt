package com.example.cinema.data.db.tables

import com.example.cinema.data.db.entities.PlaceEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object PlacesTable : Table<PlaceEntity>("places") {
    val id = int("place_id").primaryKey().bindTo { it.id }
    val row = int("row").bindTo { it.row }
    val seat = int("seat").bindTo { it.seat }
}
