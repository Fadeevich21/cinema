package com.example.cinema.ui.uiState

import com.example.cinema.domain.model.Movie

data class MovieUiState(
    val movies: List<Movie> = listOf()
)
