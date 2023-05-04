package com.example.cinema.data.db.daos

import com.example.cinema.data.db.entities.GenreEntity
import com.example.cinema.data.db.tables.GenresTable
import com.example.cinema.data.db.utils.Dao
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class GenresDao(database: Database) : Dao(database) {

    private val genres get() = database.sequenceOf(GenresTable)

    fun getAllGenres(): List<GenreEntity> {
        return genres.toList()
    }

    fun getGenreById(id: Int): GenreEntity {
        return genres.find { it.id eq id }!!
    }
}