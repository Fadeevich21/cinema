package com.example.cinema.ui.fragments.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.model.User
import com.example.cinema.domain.usecase.model.MovieUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShopViewModel(
    private val movieUseCases: MovieUseCases
) : ViewModel() {

    private var moviesLiveMutable = MutableLiveData<List<Movie>>()
    var moviesLive: LiveData<List<Movie>> = moviesLiveMutable

    private var _uiState = MutableStateFlow(ShopUiState())
    var uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(movies = movieUseCases.getAllMoviesUseCase.execute()) }
        }
    }

    fun getBoughtMoviesByUser(user: User) {
        var movies: List<Movie>
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                movies = movieUseCases.getBoughtMoviesByUserUseCase.execute(user)
            }
            withContext(Dispatchers.Main) {
                moviesLiveMutable.value = movies
            }
        }
    }
}