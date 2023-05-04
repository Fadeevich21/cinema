package com.example.cinema.ui.activies.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.model.Genre
import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.model.Privilege
import com.example.cinema.domain.model.User
import com.example.cinema.domain.usecase.BuyMovieUseCase
import com.example.cinema.domain.usecase.CheckBoughtMovieByUserUseCase
import com.example.cinema.domain.usecase.CheckUserHasPrivilegeUseCase
import com.example.cinema.domain.usecase.DeleteMovieUseCase
import com.example.cinema.domain.usecase.GetGenresByMovieIdUseCase
import com.example.cinema.domain.usecase.GetMovieByIdUseCase
import com.example.cinema.domain.usecase.GetPrivilegeByNameUseCase
import com.example.cinema.utils.Privileges
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val getGenresByMovieIdUseCase: GetGenresByMovieIdUseCase,
    private val buyMovieUseCase: BuyMovieUseCase,
    private val checkBoughtMovieByUserUseCase: CheckBoughtMovieByUserUseCase,
    private val getPrivilegeByNameUseCase: GetPrivilegeByNameUseCase,
    private val checkUserHasPrivilegeUseCase: CheckUserHasPrivilegeUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) : ViewModel() {

    private var movieLiveMutable = MutableLiveData<Movie>()
    var movieLive: LiveData<Movie> = movieLiveMutable

    private var genresLiveMutable = MutableLiveData<List<Genre>>()
    var genresLive: LiveData<List<Genre>> = genresLiveMutable

    private var isBoughtMovieLiveMutable = MutableLiveData<Boolean>(false)
    var isBoughtMovieLive: LiveData<Boolean> = isBoughtMovieLiveMutable

    private var canDeleteMovieLiveMutable = MutableLiveData<Boolean>()
    var canDeleteMovie: LiveData<Boolean> = canDeleteMovieLiveMutable

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
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                buyMovieUseCase.execute(user, movie)
            }
        }
        isBoughtMovieLiveMutable.value = true
    }

    fun checkUserCanDeleteMovie(user: User) {
        var privilege: Privilege?
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                privilege =
                    getPrivilegeByNameUseCase.execute(name = Privileges.DELETE_MOVIE.privilegeName)
            }
            var canDeleteMovie: Boolean
            withContext(Dispatchers.Main) {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        canDeleteMovie =
                            checkUserHasPrivilegeUseCase.execute(user = user, privilege = privilege)
                    }
                    withContext(Dispatchers.Main) {
                        canDeleteMovieLiveMutable.value = canDeleteMovie
                    }
                }
            }
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deleteMovieUseCase.execute(movie)
            }
        }
    }
}