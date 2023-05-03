package com.example.cinema.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.model.Genre
import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.model.User
import com.example.cinema.domain.usecase.BuyMovieUseCase
import com.example.cinema.domain.usecase.CheckBoughtMovieByUserUseCase
import com.example.cinema.domain.usecase.GetGenresByMovieIdUseCase
import com.example.cinema.domain.usecase.GetMovieByIdUseCase
import com.example.cinema.ui.uiState.MoviesDetailUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val getGenresByMovieIdUseCase: GetGenresByMovieIdUseCase,
    private val buyMovieUseCase: BuyMovieUseCase,
    private val checkBoughtMovieByUserUseCase: CheckBoughtMovieByUserUseCase
) : ViewModel() {

    private var movieLiveMutable = MutableLiveData<Movie>()
    var movieLive: LiveData<Movie> = movieLiveMutable

    private var genresLiveMutable = MutableLiveData<List<Genre>>()
    var genresLive: LiveData<List<Genre>> = genresLiveMutable

    private var isBoughtMovieLiveMutable = MutableLiveData<Boolean>(false)
    var isBoughtMovieLive: LiveData<Boolean> = isBoughtMovieLiveMutable

    private var _uiState = MutableStateFlow(MoviesDetailUiState())
    var uiState = _uiState.asStateFlow()

    // TODO: сделать так, чтобы запускалось при init
    fun checkBoughtMovieByUser(user: User, movie: Movie) {
        var isBoughtMovie: Boolean
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isBoughtMovie = checkBoughtMovieByUserUseCase.execute(user = user, movie = movie)
            }
            withContext(Dispatchers.Main) {
                isBoughtMovieLiveMutable.value = isBoughtMovie
            }
        }
    }

    fun getMovieById(movieId: Int) {
        var movie: Movie
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                movie = getMovieByIdUseCase.execute(movieId)
            }
            withContext(Dispatchers.Main) {
                movieLiveMutable.value = movie
            }
        }
    }

    fun getGenresByMovieId(movieId: Int) {
        var genres: List<Genre>
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                genres = getGenresByMovieIdUseCase.execute(movieId)
            }
            withContext(Dispatchers.Main) {
                genresLiveMutable.value = genres
            }
        }
    }

    fun buyMovie(user: User, movie: Movie) {
        Log.d("flog", "user: $user")
        Log.d("flog", "movie: $movie")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                buyMovieUseCase.execute(user, movie)
            }
        }
        isBoughtMovieLiveMutable.value = true
    }
}