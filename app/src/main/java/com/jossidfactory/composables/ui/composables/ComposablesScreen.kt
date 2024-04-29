package com.jossidfactory.composables.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.jossidfactory.composables.ui.composables.dropdown.DropDownApp
import com.jossidfactory.composables.ui.theme.PrimaryColor

@Composable
fun ComposablesScreen() {
    val focusManager = LocalFocusManager.current
    var optionSelected by remember { mutableStateOf("") }

    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
//            DropDownApp(
//                options = options,
//                borderColor = PrimaryColor,
//                borderSize = 1,
//                leadingIconColor = PrimaryColor,
//                trailingIconColor = PrimaryColor,
//                listTitle = "",
//                listTitleColor = PrimaryColor,
//                textColor = PrimaryColor,
//                optionTextColor = PrimaryColor,
//                selectedOptionTextColor = Color.White,
//                optionBackgroundColor = Color.Transparent,
//                selectedOptionBackgroundColor = PrimaryColor,
//                borderDropdownColor = PrimaryColor,
//                onOptionChange = {
//                    optionSelected = it
//                }
//            )
            DropDownApp(
                options = options,
                onOptionChange = {
                    optionSelected = it
                }
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = optionSelected)
        }
    }
}