package com.example.cinema.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.data.repository.TicketRepositoryImpl
import com.example.cinema.domain.model.Ticket
import com.example.cinema.domain.usecase.GetTicketsByUserIdUseCase
import com.example.cinema.ui.uiState.ShopUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShopViewModel : ViewModel() {
    private var ticketsLiveMutable = MutableLiveData<List<Ticket>>()
    var ticketsLive: LiveData<List<Ticket>> = ticketsLiveMutable

    private var _uiState = MutableStateFlow(ShopUiState())
    var uiState = _uiState.asStateFlow()

    private val repository = TicketRepositoryImpl()
    private val getTicketsByUserIdUseCase = GetTicketsByUserIdUseCase(repository)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(tickets = getTicketsByUserIdUseCase.execute(1)) }
        }
    }

    fun getTicketsByUserId(id: Int) {
        var tickets: List<Ticket>
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                tickets = getTicketsByUserIdUseCase.execute(id)
            }
            withContext(Dispatchers.Main) {
                ticketsLiveMutable.value = tickets
            }
        }
    }
}