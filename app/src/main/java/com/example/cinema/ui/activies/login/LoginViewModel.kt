package com.example.cinema.ui.activies.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.domain.model.User
import com.example.cinema.domain.usecase.model.UserUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private var loginUserLiveMutable = MutableLiveData<User?>()
    val loginUserLive: LiveData<User?> = loginUserLiveMutable

    private var isRegisterLiveMutable = MutableLiveData<Boolean>()
    val isRegisterLive: LiveData<Boolean> = isRegisterLiveMutable

    fun login(username: String, password: String) {
        var user: User?
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                user = userUseCases.loginUserUseCase.execute(username, password)
            }
            withContext(Dispatchers.Main) {
                loginUserLiveMutable.value = user
            }
        }
    }

    fun register(username: String, password: String) {
        var isRegister: Boolean
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isRegister = userUseCases.registerUserUseCase.execute(username, password)
            }
            withContext(Dispatchers.Main) {
                isRegisterLiveMutable.value = isRegister
            }
        }
    }
}