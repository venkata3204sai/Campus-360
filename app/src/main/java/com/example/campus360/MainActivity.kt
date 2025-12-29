package com.example.campus360

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.example.campus360.ui.theme.Campus360Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Campus360Theme {
                val nav = rememberNavController()
                NavHost(navController = nav, startDestination = "home") {
                    composable("home") { HomeScreen(nav) }
                    composable("directions/{fromId}/{toId}") { backStack ->
                        val fromId = backStack.arguments?.getString("fromId") ?: ""
                        val toId = backStack.arguments?.getString("toId") ?: ""

                        DirectionsScreen(
                            nav = nav,
                            fromId = fromId,
                            toId = toId
                        )
                    }
                    composable("find") { FindAndGoScreen(nav, applicationContext) }
                    composable("qr") {
                        QrScanScreen(nav) { scannedText ->
                            println("QR scanned: $scannedText")
                        }
                    }

                }
            }
        }
    }
}