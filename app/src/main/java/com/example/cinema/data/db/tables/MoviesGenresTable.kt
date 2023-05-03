package com.example.cinema.data.db.tables

import com.example.cinema.data.db.entities.MovieGenreEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int

object MoviesGenresTable: Table<MovieGenreEntity>("movies_genres") {

    val id = int("movie_genre_id").primaryKey().bindTo { it.id }
    val movieId = int("movie_id").references(MoviesTable) {it.movieId}
    val genreId = int("genre_id").references(GenresTable) {it.genreId}
}