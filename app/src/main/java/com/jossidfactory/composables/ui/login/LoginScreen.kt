package com.jossidfactory.composables.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jossidfactory.composables.R
import com.jossidfactory.composables.ui.composables.ButtonApp
import com.jossidfactory.composables.ui.composables.PasswordTextField
import com.jossidfactory.composables.ui.composables.TextFieldMail
import com.jossidfactory.composables.ui.composables.dialogs.InfoDialog
import com.jossidfactory.composables.ui.theme.PrimaryColor
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state: LoginScreenState by viewModel.state.observeAsState(initial = LoginScreenState())

    var isLoginScreen by remember { mutableStateOf(true) }

    val focusManager = LocalFocusManager.current
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
            val title = if (isLoginScreen) {
                stringResource(id = R.string.login_title)
            } else {
                stringResource(id = R.string.signup_title)
            }
            Text(
                text = title,
                color = PrimaryColor,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color(0xFF000000),
                        offset = Offset(2f, 2f),
                        blurRadius = 4f

                    )
                )
            )
            Spacer(modifier = Modifier.height(50.dp))
            TextFieldMail(
                email = state.email,
                focusManager = focusManager
            ) {
                viewModel.setMail(it)
            }
            Spacer(modifier = Modifier.height(50.dp))
            PasswordTextField(
                password = state.password,
                focusManager = focusManager
            ) {
                viewModel.setPassword(it)
            }

            if(!isLoginScreen) {
                Spacer(modifier = Modifier.height(50.dp))
                PasswordTextField(
                    password = state.confirmPassword,
                    focusManager = focusManager,
                    placeholder = stringResource(id = R.string.confirm_password_hint)
                ) {
                    viewModel.setConfirmPassword(it)
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            val textButton = if (isLoginScreen) {
                stringResource(id = R.string.login_button)
            } else {
                stringResource(id = R.string.signup_button)
            }
            
            ButtonApp(
                text = textButton
            ) {
                if (isLoginScreen) {
                    viewModel.login()
                } else {
                    viewModel.signUp()
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            val text = if (isLoginScreen) {
                stringResource(id = R.string.no_have_account)
            } else {
                stringResource(id = R.string.have_account)
            }

            Text(
                text = text,
                color = PrimaryColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color(0xFF000000),
                        offset = Offset(2f, 2f),
                        blurRadius = 4f

                    )
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            val clickableText = if (isLoginScreen) {
                stringResource(id = R.string.go_to_signup)
            } else {
                stringResource(id = R.string.go_to_login)
            }

            Text(
                text = clickableText,
                color = PrimaryColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color(0xFF000000),
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    ),
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier.clickable {
                    isLoginScreen = !isLoginScreen
                }
            )

            Spacer(modifier = Modifier.height(50.dp))
        }
        if(state.error.isNotEmpty()) {
            InfoDialog(
                info = state.error
            ) {
                viewModel.setError("")
            }
        }
    }
}