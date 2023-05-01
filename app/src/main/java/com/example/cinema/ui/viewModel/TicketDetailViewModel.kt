package com.example.cinema.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.data.repository.TicketRepositoryImpl
import com.example.cinema.domain.model.Ticket
import com.example.cinema.domain.usecase.BuyTicketUseCase
import com.example.cinema.domain.usecase.GetTicketByIdUseCase
import com.example.cinema.ui.uiState.TicketDetailUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TicketDetailViewModel : ViewModel() {
    private var ticketLiveMutable = MutableLiveData<Ticket>()
    var ticketLive: LiveData<Ticket> = ticketLiveMutable

    private var _uiState = MutableStateFlow(TicketDetailUiState())
    var uiState = _uiState.asStateFlow()

    private val repository = TicketRepositoryImpl()
    private val getTicketByIdUseCase = GetTicketByIdUseCase(repository)
    private val buyTicketUseCase = BuyTicketUseCase(repository)

    fun getTicketById(ticketId: Int) {
        var ticket: Ticket
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                ticket = getTicketByIdUseCase.execute(ticketId)
            }
            withContext(Dispatchers.Main) {
                ticketLiveMutable.value = ticket
            }
        }
    }

    fun buyTicket(userId: Int, ticketId: Int) {
        _uiState.value.isTicketBuy = buyTicketUseCase.execute(userId, ticketId)
    }
}