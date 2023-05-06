package com.example.cinema.data.db.daos

import com.example.cinema.data.db.entities.MovieEntity
import com.example.cinema.data.db.tables.MoviesTable
import com.example.cinema.data.db.utils.Dao
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.removeIf
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import org.ktorm.jackson.KtormModule

class MoviesDao(database: Database) : Dao(database) {

    private val movies get() = database.sequenceOf(MoviesTable)

    fun getAllMovies(): List<MovieEntity> {
        return movies.toList()
    }

    fun getMovieById(id: Int): MovieEntity {
        return movies.find { it.id eq id }!!
    }

    fun getCountMovies(): Int {
        return movies.totalRecords
    }

    fun addMovie(movie: MovieEntity): Boolean {
        movies.add(movie)
        return true
    }

    fun deleteMovie(movie: MovieEntity): Boolean {
        movies.removeIf { it.id eq movie.id }
        return true
    }

    fun getJson(movie: MovieEntity): JsonNode {
        val mapper = ObjectMapper()
        val objectMapper = mapper.registerModule(KtormModule())
        val json = objectMapper.writeValueAsString(movie)

        return mapper.readTree(json)
    }
}