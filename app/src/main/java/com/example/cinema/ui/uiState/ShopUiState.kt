package com.example.cinema.ui.uiState

import com.example.cinema.domain.model.Movie

data class ShopUiState(
    var movies: List<Movie> = listOf()
)