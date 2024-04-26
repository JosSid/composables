package com.jossidfactory.composables.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument>
) {
    data object Splash : Screen(
        route = "Splash",
        arguments = emptyList()
    )
    data object Login : Screen(
        route = "Login",
        arguments = emptyList()
    )

    data object Main : Screen(
        route = "Main",
        arguments = emptyList()
    )
}