package com.jossidfactory.composables.ui.login

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.composables.R
import com.jossidfactory.composables.data.database.dao.UserDao
import com.jossidfactory.composables.data.database.entities.UserEntity
import com.jossidfactory.composables.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val context: Context,
    private val userDao: UserDao
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

    fun setConfirmPassword(confirmPassword: String) {
        _state.value = _state.value?.copy(
            confirmPassword = confirmPassword
        )
    }

    fun setError(error: String) {
        _state.value = _state.value?.copy(
            error = error
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

        val validate = validateSignup()

        if(!validate) {
            return
        }
        val email = _state.value?.email
        val password = _state.value?.password

            viewModelScope.launch {
                val user = userDao.getUserByEmail(email!!)

                if(user != null) {
                    setError(getString(context,R.string.user_already_registered))
                    return@launch
                } else {
                    userDao.insertUser(UserEntity(email = email, password = password!!))
                    setError("Hola")
                }
//                val user = UserEntity(email = email, password = password)
//                userDao.insertUser(user)
            }
    }

    private fun validateSignup(): Boolean {
        val email = _state.value?.email
        val password = _state.value?.password
        val confirmPassword = _state.value?.confirmPassword

        if(email.isNullOrEmpty() || password.isNullOrEmpty() || confirmPassword.isNullOrEmpty()) {
            setError(getString(context,R.string.all_fields_required))
            return false
        }


        if(!Validator.isValidEmail(email!!)) {
            setError(getString(context,R.string.format_email))
            return false
        }

        if(password!!.length < 6) {
            setError(getString(context,R.string.password_length))
            return false
        }

        if(!Validator.isMatchingPasswords(password!!, confirmPassword!!)) {
            setError(getString(context,R.string.password_not_match))
            return false
        }

        return true
    }

    fun login() {
        val validate = validateLogin()

        if(!validate) {
            return
        }

        val email = _state.value?.email
        val password = _state.value?.password

        viewModelScope.launch {
            val user = userDao.getUserByEmail(email!!)

            if(user == null || user.password != password) {
                setError(getString(context,R.string.invalid_credentials))
                return@launch
            }

            setError("Hola")
        }
    }

    private fun validateLogin(): Boolean {
        val email = _state.value?.email
        val password = _state.value?.password

        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
            setError(getString(context,R.string.all_fields_required))
            return false
        }

        if(!Validator.isValidEmail(email!!)) {
            setError(getString(context,R.string.format_email))
            return false
        }

        return true
    }
}