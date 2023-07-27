package com.example.pichanga

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.traceEventStart
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.example.pichanga.data.EventDatabase
import com.example.pichanga.data.EventRepository
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(this, EventDatabase::class.java, "event_db").build()
        val dao = db.dao
        val repo = EventRepository(dao)
        val viewModel = CalendarViewModel(repo)

        setContent {
            NotificationScreen(viewModel)
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationScreen(viewModel:CalendarViewModel){
    val permissionState =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(true){
        permissionState.launchPermissionRequest()
    }

    if(permissionState.status.isGranted){
        Nav(viewModel)
    }
    Text(text = "Notification Screen")
}
