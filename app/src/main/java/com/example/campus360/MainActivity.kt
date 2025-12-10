package com.example.campus360

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.example.campus360.ui.theme.Campus360Theme


var selectedRoom: Room? = null
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Campus360Theme {
                val nav = rememberNavController()
                NavHost(navController = nav, startDestination = "home") {
                    composable("home") { HomeScreen(nav) }
                    composable("search") { SearchScreen(nav, applicationContext) }
                    composable("pois") { PoiListScreen(nav, applicationContext) }
                    composable("directions") { DirectionsScreen(nav, selectedRoom!!)}
                }
            }
        }
    }
}