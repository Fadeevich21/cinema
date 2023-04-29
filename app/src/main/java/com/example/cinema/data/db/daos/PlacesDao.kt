package com.example.cinema.data.db.daos

import com.example.cinema.data.db.entities.PlaceEntity
import com.example.cinema.data.db.tables.PlacesTable
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class PlacesDao(database: Database) : Dao(database) {
    private val places get() = database.sequenceOf(PlacesTable)

    fun getPlaceById(id: Int): PlaceEntity {
        return places.filter { it.id eq id }.toList().first()
    }
}