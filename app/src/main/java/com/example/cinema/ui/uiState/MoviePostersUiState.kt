package com.example.cinema.ui.uiState

import com.example.cinema.domain.model.MoviePoster

data class MoviePostersUiState(
    val moviePosters: List<MoviePoster> = listOf()
)
