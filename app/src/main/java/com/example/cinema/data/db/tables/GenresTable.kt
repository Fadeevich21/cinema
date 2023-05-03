package com.example.cinema.data.db.tables

import com.example.cinema.data.db.entities.GenreEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

object GenresTable : Table<GenreEntity>("genres") {

    val id = int("genre_id").primaryKey().bindTo { it.id }
    val name = text("name").bindTo { it.name }
}