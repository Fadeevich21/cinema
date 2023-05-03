package com.example.cinema.ui.uiState

import com.example.cinema.domain.model.Movie

data class HomeUiState(
    var movies: List<Movie> = listOf()
)