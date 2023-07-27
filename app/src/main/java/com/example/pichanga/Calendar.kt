package com.example.pichanga

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import com.example.pichanga.components.CreateEventDialog
import com.example.pichanga.notification.EventNotification
import com.example.pichanga.ui.theme.*
import com.mabn.calendarlibrary.ExpandableCalendar
import com.mabn.calendarlibrary.core.calendarDefaultTheme
import java.util.Calendar


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CalendarScreen(navController: NavController, viewModel: CalendarViewModel){

    val state = viewModel.state
    val context = LocalContext.current

    var showDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar { }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showDialog = true
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Crear Evento"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .background(GrayGeneral_WelcomeScreen)
        ) {
            ExpandableCalendar(
                theme = calendarDefaultTheme.copy(
                    backgroundColor = Color(0xFF37373E),
                    dayValueTextColor = textColor_CalendarScreen,
                    selectedDayBackgroundColor = buttonColor_WelcomeScreen,
                    selectedDayValueTextColor = textColor_CalendarScreen,
                    headerTextColor = textColor_CalendarScreen,
                    weekDaysTextColor = textColor_CalendarScreen,
                    dayShape = RoundedCornerShape(10.dp)
                ),
                onDayClick = {day -> viewModel.onValueChangeDate(day.toString())}
            )

            LazyColumn(
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ){
                items(state.eventList){event ->
                    TaskDesign(event = event)
                }
            }
        }

        CreateEventDialog(
            show = showDialog,
            viewModel = viewModel,
            onDismiss = {
                showDialog = false
            },
            onButtonClick = {
                viewModel.saveEvent()
                showDialog = false
            }
        )

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2023)
        calendar.set(Calendar.MONTH, Calendar.JULY)
        calendar.set(Calendar.DAY_OF_MONTH, 27)
        calendar.set(Calendar.HOUR_OF_DAY, 16)
        calendar.set(Calendar.MINUTE, 50)
        val dateTimeInMillis = calendar.timeInMillis

        viewModel.scheduleNotificationAtDateTime(1, "Título de la notificación", "Contenido de la notificación", dateTimeInMillis)
    }
}

@Composable
fun TaskDesign(event: Event){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                GrayCalendar_CalendarScreen,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = event.title,
                color = textColor_CalendarScreen,
                style = MaterialTheme.typography.h6
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 5.dp)
            ){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_event_repeat_24),
                    tint = Color.White,
                    contentDescription = "Repeat",
                    modifier = Modifier
                        .padding(end = 5.dp)
                )
                Text(
                    text = "Se repite: ${event.repeat}",
                    color = textColor_CalendarScreen
                )
            }
        }
        Column() {
            Text(
                text = event.time,
                color = textColor_CalendarScreen,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}
