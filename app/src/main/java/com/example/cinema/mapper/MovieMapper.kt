package com.example.cinema.mapper

import com.example.cinema.data.db.entities.MovieEntity
import com.example.cinema.domain.model.Movie
import com.example.cinema.mapper.utils.EntityMapper

class MovieMapper : EntityMapper<MovieEntity, Movie> {

    override fun mapFromEntity(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            duration = entity.duration,
            yearOfRelease = entity.yearOfRelease,
            ageRestriction = entity.ageRestriction,
            trailerUrl = entity.trailerUrl,
            contentUrl = entity.contentUrl
        )
    }

    override fun mapToEntity(domainModel: Movie): MovieEntity {
        return MovieEntity {
            id = domainModel.id!!
            name = domainModel.name
            description = domainModel.description
            duration = domainModel.duration
            yearOfRelease = domainModel.yearOfRelease
            ageRestriction = domainModel.ageRestriction
            trailerUrl = domainModel.trailerUrl
            contentUrl = domainModel.contentUrl
        }
    }
}