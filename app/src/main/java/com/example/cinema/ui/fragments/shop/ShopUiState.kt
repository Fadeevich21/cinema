package com.example.cinema.ui.fragments.shop

import com.example.cinema.domain.model.Movie

data class ShopUiState(
    var movies: List<Movie> = listOf()
)