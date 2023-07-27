package com.example.pichanga.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.pichanga.R

class EventNotification : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == "SHOW_NOTIFICATION"){
            val notificationId = intent.getIntExtra("NOTIFICATION_ID", 0)
            val title = intent.getStringExtra("NOTIFICATION_TITLE")
            val content = intent.getStringExtra("NOTIFICATION_CONTENT")

            val notification = NotificationCompat.Builder(context, CreateEventNotification.CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.baseline_event_24)
                .build()

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(notificationId, notification)
        }
    }
}