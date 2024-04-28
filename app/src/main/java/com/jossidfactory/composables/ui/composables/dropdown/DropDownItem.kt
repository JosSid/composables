package com.jossidfactory.composables.ui.composables.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jossidfactory.composables.ui.theme.PrimaryColor

@Composable
fun DropDownItem(
    text: String,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {

    val backgroundColor = if (isSelected) {
        PrimaryColor
    } else {
        Color.Transparent
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .clickable { onItemClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider()

    }
}