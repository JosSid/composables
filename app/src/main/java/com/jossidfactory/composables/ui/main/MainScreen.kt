package com.jossidfactory.composables.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.jossidfactory.composables.R

@Composable
fun MainScreen(
    content: @Composable () -> Unit = {}
) {
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

    content()
}

@Preview
@Composable
fun MainScreenPrev() {
    MainScreen()
}