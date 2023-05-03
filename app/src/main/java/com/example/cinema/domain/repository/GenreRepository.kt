package com.example.cinema.domain.repository

import com.example.cinema.domain.model.Genre

interface GenreRepository {

    fun getGenresByMovieId(movieId: Int): List<Genre>
}