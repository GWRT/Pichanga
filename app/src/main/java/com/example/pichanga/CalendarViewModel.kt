package com.example.pichanga

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pichanga.data.EventRepository
import com.example.pichanga.notification.CreateEventNotification
import com.example.pichanga.notification.EventNotification
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class CalendarViewModel(
    private val eventRepository: EventRepository
) : ViewModel() {
    var state by mutableStateOf(CalendarState())
        private set

    init{
        viewModelScope.launch {
            state = state.copy(
                eventList = eventRepository.getEvents()
            )
        }
    }

    fun onValueChangeTitle(title: String){
        state = state.copy(
            title = title,
        )
    }

    fun onValueChangeDate(date: String){
        state = state.copy(
            date = date,
        )
    }

    fun onValueChangeTime(time: String){
        state = state.copy(
            time = time,
        )
    }

    fun onValueChangeRepeat(repeat: String){
        state = state.copy(
            repeat = repeat,
        )
    }

    fun saveEvent(){
        val event = Event(
            title = state.title,
            date = state.date,
            time = state.time,
            repeat = state.repeat
        )
        viewModelScope.launch {
            eventRepository.insertEvent(event)
            state = state.copy(
                eventList = eventRepository.getEvents()
            )
        }
    }
    /*
    fun sendNotification(context: Context){
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(context, EventNotification.CHANNEL_ID)
            .setContentTitle(state.title)
            .setContentText("Esto es la decripcion")
            .setSmallIcon(R.drawable.baseline_event_24)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }

     */

    @Composable
    fun scheduleNotificationAtDateTime(notificationId: Int, title: String, content: String, dateTime: Long) {
        val context = LocalContext.current

        val notificationIntent = Intent(context, EventNotification::class.java).apply {
            action = "SHOW_NOTIFICATION"
            putExtra("NOTIFICATION_ID", notificationId)
            putExtra("NOTIFICATION_TITLE", title)
            putExtra("NOTIFICATION_CONTENT", content)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationId,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Set the alarm at the desired date and time
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, dateTime, pendingIntent)
    }
}