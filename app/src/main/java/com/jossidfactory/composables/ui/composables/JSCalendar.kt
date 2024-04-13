package com.jossidfactory.composables.ui.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jossidfactory.composables.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun JSCalendar() {
    var currentMonth by remember { mutableStateOf(Calendar.getInstance()) }
    var currentMonthDays by remember { mutableStateOf(generateCalendarDays(currentMonth)) }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .width(290.76587.dp)
                .height(332.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .shadow(
                        elevation = 12.dp,
                        spotColor = Color(0x45000000),
                        ambientColor = Color(0x45000000)
                    )
                    .width(290.76587.dp)
                    .defaultMinSize(minHeight = 299.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
                    .padding(start = 13.dp, top = 14.dp, end = 13.dp, bottom = 14.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                CalendarHeader(currentMonth) { newMonth ->
                    currentMonth = newMonth
                    currentMonthDays = generateCalendarDays(currentMonth)
                }

                DaysOfWeek()

                LazyColumn(
                    modifier = Modifier
                        .background(Color.White)
                ) {

                    items(currentMonthDays) { week ->
                        Row(
                            modifier = Modifier
                                .width(264.76587.dp)
                                .height(36.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            // Renderizar los días en orden
                            week.forEach { day ->
                                CalendarDay(day, currentMonth)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarHeader(currentMonth: Calendar, onMonthChange: (Calendar) -> Unit) {
    Row(
        modifier = Modifier
            .width(264.76587.dp)
            .height(36.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(
                currentMonth
                    .time
            ),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .width(100.dp)
                .height(46.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top,
        ) {
            IconButton(onClick = { onMonthChange.invoke(getPreviousMonth(currentMonth)) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "ArrowBack",
                    tint = Color(0xFF000000)
                )
            }
            IconButton(onClick = { onMonthChange.invoke(getNextMonth(currentMonth)) }) {
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "ArrowBack",
                    tint = Color(0xFF000000)
                )
            }
        }
    }

}

@Composable
fun DaysOfWeek() {
    val daysOfWeek = listOf("MO", "TU", "WE", "TH", "FR", "SA", "SU")

    Row(
        modifier = Modifier
            .width(264.76587.dp)
            .height(18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        daysOfWeek.forEach { day ->
            Box(
                modifier = Modifier
                    .size(36.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = day,
                    modifier = Modifier.size(36.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}

@Composable
fun CalendarDay(day: Calendar, currentMonth: Calendar) {
    val textColor = if (day.get(Calendar.MONTH) == currentMonth.get(Calendar.MONTH)) {
        Color(0xFF000000)
    } else {
        Color(0xFF888888)
    }
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(Color.Transparent)
            .border(width = 1.dp, color = Color(0xFFD5D4DF))
            .clickable {
                Log.d("selectedDay", "${day.get(Calendar.DAY_OF_MONTH).toString()}")
            },
        contentAlignment = Alignment.Center,

        ) {
        Text(
            text = day.get(Calendar.DAY_OF_MONTH).toString(),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                fontWeight = FontWeight(400),
                color = textColor,
                textAlign = TextAlign.Center,
            )
        )
    }
}


fun getPreviousMonth(currentMonth: Calendar): Calendar {
    val previousMonth = currentMonth.clone() as Calendar
    previousMonth.add(Calendar.MONTH, -1)
    return previousMonth
}

fun getNextMonth(currentMonth: Calendar): Calendar {
    val nextMonth = currentMonth.clone() as Calendar
    nextMonth.add(Calendar.MONTH, 1)
    return nextMonth
}


fun generateCalendarDays(currentMonth: Calendar): List<List<Calendar>> {
    val calendarList = mutableListOf<List<Calendar>>()

    val firstDay = Calendar.getInstance().apply {
        time = currentMonth.time
        set(Calendar.DAY_OF_MONTH, 1)
    }

    val lastDay = Calendar.getInstance().apply {
        time = currentMonth.time
        set(Calendar.DAY_OF_MONTH, currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH))
    }

    // Ajuste para obtener el día correcto de la semana para el primer día del mes
    while (firstDay.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
        firstDay.add(Calendar.DAY_OF_MONTH, -1)
    }

    while (lastDay.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
        lastDay.add(Calendar.DAY_OF_MONTH, 1)
    }

    var currentDay = firstDay.clone() as Calendar
    var currentWeek = mutableListOf<Calendar>()

    while (currentDay.before(lastDay) || currentDay == lastDay) {
        if (currentWeek.size == 7) {
            calendarList.add(currentWeek.toList())
            currentWeek = mutableListOf()
        }

        currentWeek.add(currentDay.clone() as Calendar)
        currentDay.add(Calendar.DAY_OF_MONTH, 1)
    }

    // Añadir la última semana si aún no se ha agregado
    if (currentWeek.isNotEmpty()) {
        calendarList.add(currentWeek.toList())
    }

    return calendarList
}


@Preview
@Composable
fun JSCalendarPrev() {
    JSCalendar()
}
