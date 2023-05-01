package com.example.cinema.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.data.repository.TicketRepositoryImpl
import com.example.cinema.domain.model.Ticket
import com.example.cinema.domain.usecase.GetAllTicketsUseCase
import com.example.cinema.ui.uiState.HomeUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class HomeViewModel : ViewModel() {
    private var ticketsLiveMutable = MutableLiveData<List<Ticket>>()
    var ticketsLive: LiveData<List<Ticket>> = ticketsLiveMutable

    private var _uiState = MutableStateFlow(HomeUiState())
    var uiState = _uiState.asStateFlow()

    private val repository = TicketRepositoryImpl()
    private val getAllTicketsUseCase = GetAllTicketsUseCase(repository)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(tickets = getAllTicketsUseCase.execute()) }
        }
    }

    fun getAllTickets() {
        var tickets: List<Ticket>
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                tickets = getAllTicketsUseCase.execute()
            }
            withContext(Dispatchers.Main) {
                ticketsLiveMutable.value = tickets
            }
        }
    }

    fun filter(name: String) {
        val filteredTickets = mutableListOf<Ticket>()
        for (ticket in uiState.value.tickets) {
            if (ticket.movie.name.lowercase().contains(name.lowercase(Locale.getDefault())))
                filteredTickets.add(ticket)

            ticketsLiveMutable.value = filteredTickets
        }
    }
}