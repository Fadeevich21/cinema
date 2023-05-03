package com.example.cinema.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.model.User
import com.example.cinema.domain.usecase.GetAllMoviesUseCase
import com.example.cinema.domain.usecase.GetBoughtMoviesByUserUseCase
import com.example.cinema.ui.uiState.ShopUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShopViewModel(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val getBoughtMoviesByUserUseCase: GetBoughtMoviesByUserUseCase
) : ViewModel() {

    private var moviesLiveMutable = MutableLiveData<List<Movie>>()
    var moviesLive: LiveData<List<Movie>> = moviesLiveMutable

    private var _uiState = MutableStateFlow(ShopUiState())
    var uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(movies = getAllMoviesUseCase.execute()) }
        }
    }

    fun getBoughtMoviesByUser(user: User) {
        var movies: List<Movie>
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                movies = getBoughtMoviesByUserUseCase.execute(user)
            }
            withContext(Dispatchers.Main) {
                moviesLiveMutable.value = movies
            }
        }
    }
}