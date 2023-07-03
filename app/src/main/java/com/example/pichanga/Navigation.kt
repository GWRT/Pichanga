package com.example.pichanga

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Nav(){
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ){
        composable(route = "welcome") {
            WelcomeScreen(navController)
        }
        composable(route = "calendar"){
            CalendarScreen(navController)
        }
    }

}