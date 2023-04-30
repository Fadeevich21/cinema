package com.example.cinema.domain.repository

import com.example.cinema.domain.model.MoviePoster

interface MoviePosterRepository {
    fun getMoviePosterById(id: Int): MoviePoster
    fun getAllMoviePosters(): List<MoviePoster>
}