package com.jossidfactory.composables.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val loginState = LoginScreenState()


    private val _state = MutableLiveData<LoginScreenState>()
    val state: LiveData<LoginScreenState> = _state

    init {
        _state.value = loginState
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
}