package com.jossidfactory.composables.utils

object Validator {
    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")
        return emailRegex.matches(email)
    }

    fun isMatchingPasswords(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

}