package com.jossidfactory.composables.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldMail(
    email: String,
    focusManager: FocusManager?,
    onEmailChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
            .background(Color.White, RoundedCornerShape(50.dp))
            .border(1.dp, Color(0xFF4CA2E6), RoundedCornerShape(50.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Outlined.Email,
            contentDescription = "",
            tint = Color(0xFF4CA2E6),
            modifier = Modifier.padding(start = 20.dp)
        )
        TextField(
            value = email,
            onValueChange = {
                onEmailChange(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
                //.padding(horizontal = 4.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Email
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Handle login
                    focusManager?.clearFocus()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF4CA2E6),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = "Email",
                    color = Color(0xFF4CA2E6)
                )
            }
        )
    }
}