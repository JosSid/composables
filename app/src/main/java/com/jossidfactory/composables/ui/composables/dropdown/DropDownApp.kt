package com.jossidfactory.composables.ui.composables.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.TextFields
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
/**
 * DropDownApp composable that displays a dropdown menu
 * @param options list of options to be displayed in the dropdown
 * @param focusManager FocusManager used to manage focus
 * @param placeholder text to be displayed when no option is selected
 * @param leadingIcon ImageVector to be displayed at the start of the TextField
 * @param leadingIconColor Color of the leading icon
 * @param leadingIconVisibility Boolean to determine if the leading icon is visible
 * @param resetOption Boolean to determine if the option should be reset
 * @param onOptionChange callback function that returns the selected option
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownApp(
    options: List<String> = listOf("Option 1", "Option 2", "Option 3"),
    focusManager: FocusManager? = null,
    placeholder: String = "Options",
    leadingIcon: ImageVector? = null,
    leadingIconColor: Color = Color(0xFF4CA2E6),
    leadingIconVisibility: Boolean = true,
    resetOption: Boolean = false,
    onOptionChange: (String) -> Unit = {}
) {

    var showDropDown by remember { mutableStateOf(false) }

    var optionSelected by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
            .background(Color.White, RoundedCornerShape(50.dp))
            .border(1.dp, Color(0xFF4CA2E6), RoundedCornerShape(50.dp)),
            //.height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        val iconColor = if(leadingIconVisibility) leadingIconColor else Color.Transparent

        Icon(
            imageVector = leadingIcon ?: Icons.Outlined.TextFields,
            contentDescription = "",
            tint = iconColor,
            modifier = Modifier.padding(start = 20.dp)
        )
        TextField(
            value = optionSelected,
            readOnly = true,
            onValueChange = {
                optionSelected = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            //.padding(horizontal = 4.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            //visualTransformation = if(visible) VisualTransformation.None else
                //PasswordVisualTransformation(),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager?.clearFocus()
                }
            ),
            trailingIcon = {
                val iconArrow = if(!showDropDown) Icons.Outlined.KeyboardArrowDown else Icons.Outlined
                    .KeyboardArrowUp
                IconButton(
                    modifier = Modifier.padding(end = 20.dp),
                    onClick = { showDropDown = !showDropDown }
                ) {
                    Icon(
                        imageVector = iconArrow,
                        contentDescription = "",
                        tint = Color(0xFF4CA2E6),
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color(0xFF4CA2E6),
                unfocusedTextColor = Color(0xFF4CA2E6),
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

    if(showDropDown) {
        DropDownContent(
            list = options,
            resetOption = resetOption,
            optionSelected = optionSelected,
            onItemSelected = {
                optionSelected = it
                showDropDown = false
                onOptionChange(it)
            })
    }
}