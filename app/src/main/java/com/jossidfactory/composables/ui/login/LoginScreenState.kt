package com.jossidfactory.composables.ui.login

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String = ""
)
