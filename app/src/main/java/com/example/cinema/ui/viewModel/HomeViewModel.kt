package com.example.cinema.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.usecase.FilterMoviesByNameUseCase
import com.example.cinema.domain.usecase.GetAllMoviesUseCase
import com.example.cinema.ui.uiState.HomeUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val filterMoviesByNameUseCase: FilterMoviesByNameUseCase
) : ViewModel() {

    private var moviesLiveMutable = MutableLiveData<List<Movie>>()
    var moviesLive: LiveData<List<Movie>> = moviesLiveMutable

    private var _uiState = MutableStateFlow(HomeUiState())
    var uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(movies = getAllMoviesUseCase.execute()) }
        }
    }

    fun getAllMovies() {
        var movies: List<Movie>
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                movies = getAllMoviesUseCase.execute()
            }
            withContext(Dispatchers.Main) {
                moviesLiveMutable.value = movies
            }
        }
    }

    fun filter(name: String) {
        moviesLiveMutable.value = filterMoviesByNameUseCase.execute(_uiState.value.movies, name)
    }
}