package com.example.cinema.database.tables

import com.example.cinema.database.entities.MovieEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

object MovieTable: Table<MovieEntity>("movies") {

    val id = int("movie_id").primaryKey().bindTo { it.id }
    val name = text("name").bindTo { it.name }
    val description = text("description").bindTo { it.description }
    val duration = text("duration").bindTo { it.duration }
}