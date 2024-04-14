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
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    password: String,
    focusManager: FocusManager?,
    placeholder: String = "Password",
    onPasswordChange: (String) -> Unit
) {

    var visible by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
            .background(Color.White, RoundedCornerShape(50.dp))
            .border(1.dp, Color(0xFF4CA2E6), RoundedCornerShape(50.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Outlined.Lock,
            contentDescription = "",
            tint = Color(0xFF4CA2E6),
            modifier = Modifier.padding(start = 20.dp)
        )
        TextField(
            value = password,
            onValueChange = {
                onPasswordChange(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            //.padding(horizontal = 4.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if(visible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager?.clearFocus()
                }
            ),
            trailingIcon = {
                val iconEye = if(!visible) Icons.Outlined.Visibility else Icons.Outlined
                    .VisibilityOff
                IconButton(
                    modifier = Modifier.padding(end = 20.dp),
                    onClick = { visible = !visible }
                ) {
                    Icon(
                        imageVector = iconEye,
                        contentDescription = "",
                        tint = Color(0xFF4CA2E6),
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF4CA2E6),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF4CA2E6)
                )
            }
        )
    }
}