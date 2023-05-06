package com.example.cinema.ui.activies.movieDetail

import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.model.Genre
import com.example.cinema.domain.model.Movie
import com.example.cinema.domain.model.Privilege
import com.example.cinema.domain.model.User
import com.example.cinema.domain.usecase.model.FileUseCases
import com.example.cinema.domain.usecase.model.GenreUseCases
import com.example.cinema.domain.usecase.model.MovieUseCases
import com.example.cinema.domain.usecase.model.PrivilegeUseCases
import com.example.cinema.domain.usecase.model.UserUseCases
import com.example.cinema.utils.Privileges
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(
    private val movieUseCases: MovieUseCases,
    private val genreUseCases: GenreUseCases,
    private val privilegeUseCases: PrivilegeUseCases,
    private val userUseCases: UserUseCases,
    private val fileUseCases: FileUseCases
) : ViewModel() {

    private var movieLiveMutable = MutableLiveData<Movie>()
    var movieLive: LiveData<Movie> = movieLiveMutable

    private var genresLiveMutable = MutableLiveData<List<Genre>>()
    var genresLive: LiveData<List<Genre>> = genresLiveMutable

    private var isBoughtMovieLiveMutable = MutableLiveData<Boolean>(false)
    var isBoughtMovieLive: LiveData<Boolean> = isBoughtMovieLiveMutable

    private var canDeleteMovieLiveMutable = MutableLiveData<Boolean>()
    var canDeleteMovie: LiveData<Boolean> = canDeleteMovieLiveMutable

    private var canSaveMovieLiveMutable = MutableLiveData<Boolean>()
    var canSaveMovie: LiveData<Boolean> = canSaveMovieLiveMutable

    private var _uiState = MutableStateFlow(MoviesDetailUiState())
    var uiState = _uiState.asStateFlow()

    fun checkBoughtMovieByUser(user: User, movie: Movie) {
        var isBoughtMovie: Boolean
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isBoughtMovie =
                    movieUseCases.checkBoughtMovieByUserUseCase.execute(user = user, movie = movie)
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
                movie = movieUseCases.getMovieByIdUseCase.execute(movieId)
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
                genres = genreUseCases.getGenresByMovieIdUseCase.execute(movieId)
            }
            withContext(Dispatchers.Main) {
                genresLiveMutable.value = genres
            }
        }
    }

    fun buyMovie(user: User, movie: Movie) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                movieUseCases.buyMovieUseCase.execute(user, movie)
            }
        }
        isBoughtMovieLiveMutable.value = true
    }

    fun checkUserCanDeleteMovie(user: User) {
        var privilege: Privilege?
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                privilege =
                    privilegeUseCases.getPrivilegeByNameUseCase.execute(name = Privileges.DELETE_MOVIE.privilegeName)
            }
            var canDeleteMovie: Boolean
            withContext(Dispatchers.Main) {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        canDeleteMovie =
                            userUseCases.checkUserHasPrivilegeUseCase.execute(
                                user = user,
                                privilege = privilege
                            )
                    }
                    withContext(Dispatchers.Main) {
                        canDeleteMovieLiveMutable.value = canDeleteMovie
                    }
                }
            }
        }
    }

    fun checkUserCanSaveMovie(user: User) {
        var privilege: Privilege?
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                privilege =
                    privilegeUseCases.getPrivilegeByNameUseCase.execute(name = Privileges.SAVE_MOVIE.privilegeName)
            }
            var canDeleteMovie: Boolean
            withContext(Dispatchers.Main) {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        canDeleteMovie =
                            userUseCases.checkUserHasPrivilegeUseCase.execute(
                                user = user,
                                privilege = privilege
                            )
                    }
                    withContext(Dispatchers.Main) {
                        canSaveMovieLiveMutable.value = canDeleteMovie
                    }
                }
            }
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                movieUseCases.deleteMovieUseCase.execute(movie)
            }
        }
    }

    fun getIntentForSaveMovieDetail(filename: String): Intent {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
            putExtra(Intent.EXTRA_TITLE, filename)
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, "")
        }

        return intent
    }

    fun saveMovieDetail(data: Intent?) {
        val uri = data!!.data
        val movie = movieLive.value as Movie

        val regexType: (String) -> Regex = { type -> Regex(".*.$type(\\s\\(.*\\))?$") }
        val isFilenameMatches: (Regex) -> Boolean = { getFilename(uri!!)?.matches(it) == true }
        when {
            isFilenameMatches(regexType("json")) -> saveMovieJson(uri, movie)
            isFilenameMatches(regexType("csv")) -> saveMovieCsv(uri, movie)
        }
    }

    private fun getFilename(uri: Uri): String? {
        return fileUseCases.getFilenameUseCase.execute(uri)
    }

    private fun saveMovieJson(uri: Uri?, movie: Movie) {
        movieUseCases.saveMovieJsonUseCase.execute(uri, movie)
    }

    private fun saveMovieCsv(uri: Uri?, movie: Movie) {
        movieUseCases.saveMovieCsvUseCase.execute(uri, movie)
    }
}