package com.jossidfactory.composables.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jossidfactory.composables.R
import com.jossidfactory.composables.ui.composables.ComposablesScreen
import com.jossidfactory.composables.ui.composables.JSCalendar
import com.jossidfactory.composables.ui.main.MainScreen

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel()
) {

    val state: SplashScreenState by viewModel.state.observeAsState(initial = SplashScreenState())

    if(state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFF8000)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo")
        }
    } else {
        if(state.isUserLoggedIn) {
            MainScreen {
                JSCalendar()
            }
        } else {
            MainScreen {
                //DropDownApp()
                //LoginScreen()
                ComposablesScreen()
            }
        }
    }
}