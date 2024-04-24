package com.jossidfactory.composables.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.composables.data.database.dao.UserDao
import com.jossidfactory.composables.data.database.entities.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val userDao: UserDao
) : ViewModel() {

    private val loginState = LoginScreenState()


    private val _state = MutableLiveData<LoginScreenState>()
    val state: LiveData<LoginScreenState> = _state

    init {
        _state.value = loginState
        getUsers()
    }

    fun setMail(email: String) {
        _state.value = _state.value?.copy(
            email = email
        )
    }

    fun setPassword(password: String) {
        _state.value = _state.value?.copy(
            password = password
        )
    }

    fun getUsers() {
        viewModelScope.launch {
            val users = userDao.getAllUsers()
            if (users.isNullOrEmpty())
                Log.d("Users", "No users found")
            else
                Log.d("Users", users.toString())
        }
    }

    fun signUp() {
        val email = _state.value?.email
        val password = _state.value?.password
        if (email != null && password != null) {
            viewModelScope.launch {
                val user = UserEntity(email = email, password = password)
                userDao.insertUser(user)
            }
        }
    }
}