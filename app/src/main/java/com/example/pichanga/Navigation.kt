package com.example.pichanga

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.pichanga.data.EventDatabase
import com.example.pichanga.data.EventRepository

@Composable
fun Nav(viewModel: CalendarViewModel){

    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "calendar"
    ){
        composable(route = "welcome") {
            WelcomeScreen(navController)
        }
        composable(route = "calendar"){
            CalendarScreen(navController, viewModel)
        }
    }

}