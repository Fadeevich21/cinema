package com.example.cinema.ui.activies.addMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.usecase.model.MovieUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddMovieViewModel(private val movieUseCases: MovieUseCases) : ViewModel() {

    private var isAddedMovieLiveMutable = MutableLiveData<Boolean>()
    var isAddedMovieLive: LiveData<Boolean> = isAddedMovieLiveMutable

    fun addMovie(movie: Movie) {
        var isAddedMovie: Boolean
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isAddedMovie = movieUseCases.addMovieUseCase.execute(movie = movie)
            }
            withContext(Dispatchers.Main) {
                isAddedMovieLiveMutable.value = isAddedMovie
            }
        }
    }
}