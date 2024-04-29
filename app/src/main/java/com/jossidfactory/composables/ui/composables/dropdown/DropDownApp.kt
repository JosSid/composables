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
    * @param options list of options to be displayed in the dropdown
    * @param placeholder text to be displayed in the text field
    * @param borderColor color of the border
    * @param borderSize size of the border
    * @param borderShape shape of the border
    * @param containerColor color of the container
    * @param textColor color of the text
    * @param leadingIcon icon to be displayed at the start of the text field
    * @param leadingIconColor color of the leading icon
    * @param leadingIconVisibility visibility of the leading icon
    * @param trailingIconColor color of the trailing icon
    * @param listTitleColor color of the list title
    * @param listTitle title of the list
    * @param optionTextColor color of the options
    * @param selectedOptionTextColor color of the selected option
    * @param optionBackgroundColor background color of the options
    * @param selectedOptionBackgroundColor background color of the selected option
    * @param borderDropdownColor color of the dropdown border
    * @param resetOption can reset the option
    * @param onOptionChange callback when an option is selected
*/



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownApp(
    options: List<String> = emptyList(),
    //focusManager: FocusManager? = null,
    placeholder: String = "Options",
    borderColor: Color = Color.Black,
    borderSize: Int = 1,
    borderShape: Int = 50,
    containerColor: Color? = null,
    textColor: Color = Color.Black,
    leadingIcon: ImageVector? = null,
    leadingIconColor: Color = Color.Black,
    leadingIconVisibility: Boolean = true,
    trailingIconColor: Color = Color.Black,
    listTitleColor: Color = Color.Black,
    listTitle: String = "Select an option",
    optionTextColor: Color = Color.Black,
    selectedOptionTextColor: Color = Color.White,
    optionBackgroundColor: Color = Color.Transparent,
    selectedOptionBackgroundColor: Color = Color.Black,
    borderDropdownColor: Color = Color.Black,
    resetOption: Boolean = false,
    onOptionChange: (String) -> Unit = {}
) {

    var showDropDown by remember { mutableStateOf(false) }

    var optionSelected by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
            .background(containerColor ?: Color.White, RoundedCornerShape(borderShape.dp))
            .border(borderSize.dp, borderColor, RoundedCornerShape(borderShape.dp)),
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
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Done,
//                keyboardType = KeyboardType.Password
//            ),
//            keyboardActions = KeyboardActions(
//                onDone = {
//                    focusManager?.clearFocus()
//                }
//            ),
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
                        tint = trailingIconColor,
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    color = textColor
                )
            }
        )
    }

    if(showDropDown) {
        DropDownContent(
            list = options,
            resetOption = resetOption,
            listTitleColor = listTitleColor,
            listTitle = listTitle,
            optionTextColor = optionTextColor,
            selectedOptionTextColor = selectedOptionTextColor,
            optionBackgroundColor = optionBackgroundColor,
            selectedOptionBackgroundColor = selectedOptionBackgroundColor,
            borderDropdownColor = borderDropdownColor,
            optionSelected = optionSelected,
            onItemSelected = {
                optionSelected = it
                showDropDown = false
                onOptionChange(it)
            })
    }
}