package com.example.cinema.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.data.repository.MoviePosterRepositoryImpl
import com.example.cinema.domain.model.MoviePoster
import com.example.cinema.domain.usecase.GetMoviePosterByIdUseCase
import com.example.cinema.ui.uiState.MoviePosterDetailUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviePosterDetailViewModel : ViewModel() {
    private var moviePosterLiveMutable = MutableLiveData<MoviePoster>()
    var moviePosterLive: LiveData<MoviePoster> = moviePosterLiveMutable

    private var _uiState = MutableStateFlow(MoviePosterDetailUiState())
    var uiState = _uiState.asStateFlow()

    private val repository = MoviePosterRepositoryImpl()
    private val getMoviePosterByIdUseCase = GetMoviePosterByIdUseCase(repository)

    fun getMoviePosterById(moviePosterId: Int) {
        var moviePoster: MoviePoster
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                moviePoster = getMoviePosterByIdUseCase.execute(moviePosterId)
            }
            withContext(Dispatchers.Main) {
                moviePosterLiveMutable.value = moviePoster
            }
        }
    }
}