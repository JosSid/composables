package com.jossidfactory.composables.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.composables.data.database.dao.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel@Inject constructor(
    private val userDao: UserDao
): ViewModel(){

    private val splashState = SplashScreenState()

    private val _state = MutableLiveData<SplashScreenState>()
    val state: LiveData<SplashScreenState> = _state

    init {
        _state.value = splashState
        checkUser()
    }

    private fun checkUser() {
        viewModelScope.launch {
            val users = userDao.getAllUsers()
            val user = users.find { it.logged }
            if (user != null) {
                _state.value = _state.value?.copy(
                    isUserLoggedIn = true,
                    isLoading = false
                )
                Log.d("SplashViewModel", "User is logged in")
            } else {
                _state.value = _state.value?.copy(
                    isUserLoggedIn = false,
                    isLoading = false
                )
                Log.d("SplashViewModel", "User is not logged in")
            }
        }
    }
}