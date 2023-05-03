package com.example.cinema.mapper

import com.example.cinema.data.db.entities.GenreEntity
import com.example.cinema.domain.model.Genre
import com.example.cinema.utils.EntityMapper

class GenreMapper : EntityMapper<GenreEntity, Genre> {

    override fun mapFromEntity(entity: GenreEntity): Genre {
        return Genre(
            id = entity.id,
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: Genre): GenreEntity {
        return GenreEntity {
            id = domainModel.id
            name = domainModel.name
        }
    }
}