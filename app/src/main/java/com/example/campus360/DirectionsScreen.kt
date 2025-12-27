package com.example.campus360

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.json.JSONObject

@Composable
fun DirectionsScreen(
    nav: NavHostController,
    fromId: String,
    toId: String
) {
    val context = androidx.compose.ui.platform.LocalContext.current

    // Load rooms
    val rooms = remember {
        val jsonText = context.assets
            .open("rooms.json")
            .bufferedReader()
            .use { it.readText() }

        val root = JSONObject(jsonText)
        Room.fromJsonArray(root.getJSONArray("rooms"))
    }

    val roomsById = remember { rooms.associateBy { it.id } }

    // Load graph
    val graph = remember { GraphModel.loadGraph(context) }

    // Compute path
    val path = remember(fromId, toId) {
        PathFinder.findPath(graph, fromId, toId)
    }

    // Convert to text steps
    val steps = remember(path) {
        pathToSteps(path, roomsById)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text("Directions", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        Text("From: $fromId")
        Text("To: $toId")

        Spacer(modifier = Modifier.height(16.dp))

        steps.forEachIndexed { index, step ->
            Text("${index + 1}. $step", modifier = Modifier.padding(vertical = 6.dp))
            Divider()
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { nav.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}