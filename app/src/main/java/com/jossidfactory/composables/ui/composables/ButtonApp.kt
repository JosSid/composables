package com.jossidfactory.composables.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jossidfactory.composables.R
import com.jossidfactory.composables.ui.theme.buttonAppHeight
import com.jossidfactory.composables.ui.theme.cornerShape
import com.jossidfactory.composables.ui.theme.globalPadding
import com.jossidfactory.composables.ui.theme.horizontalPadding

@Composable
fun ButtonApp(
    text: String = "ButtonApp",
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(buttonAppHeight)
            .padding(horizontal = horizontalPadding)
            .clip(RoundedCornerShape(cornerShape))
            .background(color = Color(0xFF4CA2E6))
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(cornerShape))
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = Color.White,
            )
    }
}

@Preview
@Composable
fun ButtonAppPrev() {
    ButtonApp {}

}