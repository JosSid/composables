package com.jossidfactory.composables.ui.composables.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jossidfactory.composables.R
import com.jossidfactory.composables.ui.composables.ButtonApp
import com.jossidfactory.composables.ui.theme.PrimaryColor

@Composable
fun InfoDialog(
    info: String,
    onClose: () -> Unit = {}
) {
    BaseDialog(
        modifier = Modifier
            .shadow(
                elevation = 11.dp,
                spotColor = Color(0x29000000),
                ambientColor = Color(0x29000000)
            )
            .width(321.dp)
            .background(color = Color(0xFFEFF7FF), shape = RoundedCornerShape(size = 20.dp)),
        onClose = { onClose() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(16.dp)
        ) {
//            val text = buildAnnotatedString {
//                append("Hemos detectado que en\nsu usuario ha indicado que es ")
//                withStyle(
//                    style = SpanStyle(
//                        fontWeight = FontWeight(700), fontFamily = FontFamily
//                            (Font(R.font.montserrat_bold))
//                    )
//                ) {
//                    append("mayor de edad")
//                }
//                append(". Si deseas coger un servicio para un\n" +
//                        "familiar que sea menor de edad deberás registrar dicho usuario en la " +
//                        "aplicación.")
//            }
            Text(
                text = info,
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 25.sp,
                    //fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    fontWeight = FontWeight(400),
                    color = PrimaryColor,
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonApp(
                text = stringResource(id = R.string.accept),
            ) {
                onClose()
            }
        }

    }
}