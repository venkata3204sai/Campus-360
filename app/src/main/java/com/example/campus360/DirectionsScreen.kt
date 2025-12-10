package com.example.campus360

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DirectionsScreen(nav: NavHostController, room: Room) {

    var navigating by remember { mutableStateOf(false) }

    val steps = listOf(
        "Enter Building ${room.building}.",
        "Go to Floor ${room.floor} using stairs or elevator.",
        "Walk along the main corridor.",
        "Look for room ${room.name}.",
        "You have arrived at your destination."
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text(
            text = "Directions to ${room.name}",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(steps) { index, step ->
                Text("${index + 1}. $step", modifier = Modifier.padding(8.dp))
                Divider()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            if (!navigating) {
                Button(onClick = { navigating = true }) {
                    Text("Start")
                }
            } else {
                Button(onClick = { navigating = false }) {
                    Text("Cancel")
                }
            }
        }
    }
}