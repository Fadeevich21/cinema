package com.example.cinema.data.repository

import com.example.cinema.data.db.AppDatabase
import com.example.cinema.domain.model.Genre
import com.example.cinema.domain.repository.GenreRepository
import com.example.cinema.mapper.GenreMapper

class GenreRepositoryImpl(private val genreMapper: GenreMapper) : GenreRepository {

    override fun getGenresByMovieId(movieId: Int): List<Genre> {
        return genreMapper.fromEntityList(AppDatabase.daos.moviesGenresDao.getGenresByMovieId(movieId))
    }
}