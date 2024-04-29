package com.jossidfactory.composables.ui.composables.dropdown

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jossidfactory.composables.ui.composables.dialogs.BaseDialog

@Composable
fun DropDownContent(
    list: List<String>,
    listTitle: String,
    listTitleColor: Color = Color.Black,
    optionTextColor: Color = Color.Black,
    selectedOptionTextColor: Color = Color.White,
    optionBackgroundColor: Color = Color.White,
    selectedOptionBackgroundColor: Color = Color.Black,
    borderDropdownColor: Color = Color.Black,
    resetOption: Boolean,
    optionSelected: String,
    onItemSelected: (String) -> Unit
) {

    /*val list = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5", "Option 6", "" +
     " + Option 7", "Option 8", "Option 9", "Option 10", "Option 11", "Option 12", "Option 13",
        "Option 14", "Option 15", "Option 16", "Option 17", "Option 18", "Option 19", "Option20",
        "Option 21", "Option 22", "Option 23", "Option 24", "Option 25", "Option 26", "Option " +
                "27", "Option 28", "Option 29", "Option 30", "Option 31", "Option 32", "Option 33", "Option 34", "Option 35", "Option 36", "Option 37", "Option 38", "Option 39", "Option 40", "Option 41", "Option 42", "Option 43", "Option 44", "Option 45", "Option 46", "Option 47", "Option 48", "Option 49",)*/


    val closeText = if (resetOption) "" else optionSelected
    DropDownBase(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        iconTintColor = borderDropdownColor,
        onClose = { onItemSelected(closeText) }
    ) {
        Column {
            Text(
                text = listTitle,
                color = listTitleColor,
                modifier = Modifier.padding(10.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .border(1.dp, borderDropdownColor, RoundedCornerShape(5.dp)).padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                list.forEach {
                    val isSelected = it == optionSelected
                    DropDownItem(
                        text = it,
                        isSelected = isSelected,
                        optionTextColor = optionTextColor,
                        selectedOptionTextColor = selectedOptionTextColor,
                        optionBackgroundColor = optionBackgroundColor,
                        selectedOptionBackgroundColor = selectedOptionBackgroundColor,
                        onItemClick = {
                            onItemSelected(it)
                        }
                    )
                }
            }
        }
    }
}