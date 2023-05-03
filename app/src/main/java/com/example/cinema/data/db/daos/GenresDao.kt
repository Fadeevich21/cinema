package com.example.cinema.data.db.daos

import com.example.cinema.data.db.entities.GenreEntity
import com.example.cinema.data.db.tables.GenresTable
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class GenresDao(database: Database) : Dao(database) {

    private val genres get() = database.sequenceOf(GenresTable)

    fun getAllGenres(): List<GenreEntity> {
        return genres.toList()
    }

    fun getGenreById(id: Int): GenreEntity {
        return genres.filter { it.id eq id }.toList()[0]
    }
}