package com.example.cinema.data.db.tables

import com.example.cinema.data.db.entities.MovieEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

object MoviesTable : Table<MovieEntity>("movies") {

    val id = int("movie_id").primaryKey().bindTo { it.id }
    val name = text("name").bindTo { it.name }
    val description = text("description").bindTo { it.description }
    val duration = text("duration").bindTo { it.duration }
    val yearOfRelease = int("year_of_release").bindTo { it.yearOfRelease }
    val ageRestriction = int("age_restriction").bindTo { it.ageRestriction }
    val trailerUrl = text("trailer_url").bindTo { it.trailerUrl }
}