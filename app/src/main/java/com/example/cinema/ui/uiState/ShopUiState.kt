package com.example.cinema.ui.uiState

import com.example.cinema.domain.model.Ticket

data class ShopUiState(
    var tickets: List<Ticket> = listOf()
)