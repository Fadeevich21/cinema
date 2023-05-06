package com.example.cinema.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.model.Privilege
import com.example.cinema.domain.model.User
import com.example.cinema.domain.usecase.model.MovieUseCases
import com.example.cinema.domain.usecase.model.PrivilegeUseCases
import com.example.cinema.domain.usecase.model.UserUseCases
import com.example.cinema.utils.Privileges
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val movieUseCases: MovieUseCases,
    private val userUseCases: UserUseCases,
    private val privilegeUseCases: PrivilegeUseCases
) : ViewModel() {

    private var moviesLiveMutable = MutableLiveData<List<Movie>>()
    var moviesLive: LiveData<List<Movie>> = moviesLiveMutable

    private var canAddMovieLiveMutable = MutableLiveData<Boolean>()
    var canAddMovieLive: LiveData<Boolean> = canAddMovieLiveMutable

    private var _uiState = MutableStateFlow(HomeUiState())
    var uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(movies = movieUseCases.getAllMoviesUseCase.execute()) }
        }
    }

    fun getAllMovies() {
        var movies: List<Movie>
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                movies = movieUseCases.getAllMoviesUseCase.execute()
            }
            withContext(Dispatchers.Main) {
                moviesLiveMutable.value = movies
            }
        }
    }

    fun checkUserCanAddMovie(user: User) {
        var privilege: Privilege?
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                privilege =
                    privilegeUseCases.getPrivilegeByNameUseCase.execute(
                        name = Privileges.ADD_MOVIE.privilegeName
                    )
            }
            var canAddMovie: Boolean
            withContext(Dispatchers.Main) {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        canAddMovie =
                            userUseCases.checkUserHasPrivilegeUseCase.execute(
                                user = user,
                                privilege = privilege
                            )
                    }
                    withContext(Dispatchers.Main) {
                        canAddMovieLiveMutable.value = canAddMovie
                    }
                }
            }
        }
    }

    fun filter(name: String) {
        moviesLiveMutable.value =
            movieUseCases.filterMoviesByNameUseCase.execute(_uiState.value.movies, name)
    }
}