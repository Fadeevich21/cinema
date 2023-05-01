package com.example.cinema.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.data.repository.MoviePosterRepositoryImpl
import com.example.cinema.domain.model.MoviePoster
import com.example.cinema.domain.usecase.GetAllMoviePostersUseCase
import com.example.cinema.ui.uiState.MoviePostersUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class MoviePostersViewModel : ViewModel() {

    private var moviePostersLiveMutable = MutableLiveData<List<MoviePoster>>()
    var moviePostersLive: LiveData<List<MoviePoster>> = moviePostersLiveMutable

    private var _uiState = MutableStateFlow(MoviePostersUiState())
    var uiState = _uiState.asStateFlow()

    private val repository = MoviePosterRepositoryImpl()
    private val getAllMoviePostersUseCase = GetAllMoviePostersUseCase(repository)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(moviePosters = getAllMoviePostersUseCase.execute()) }
        }
    }

    fun getAllMoviePosters() {
        var moviePosters: List<MoviePoster>
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                moviePosters = getAllMoviePostersUseCase.execute()
            }
            withContext(Dispatchers.Main) {
                moviePostersLiveMutable.value = moviePosters
            }
        }
    }

    fun filter(name: String) {
        val filteredMoviePosters = mutableListOf<MoviePoster>()
        for (moviePoster in uiState.value.moviePosters)
            if (moviePoster.name.lowercase().contains(name.lowercase(Locale.getDefault())))
                filteredMoviePosters.add(moviePoster)

        moviePostersLiveMutable.value = filteredMoviePosters
    }
}