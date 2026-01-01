package com.example.campus360.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.campus360.data.model.GraphModel
import com.example.campus360.data.model.Room
import com.example.campus360.domain.PathFinder
import com.example.campus360.domain.TimeEstimator
import com.example.campus360.domain.pathToSteps
import org.json.JSONObject

@Composable
fun DirectionsScreen(
    nav: NavHostController,
    fromId: String,
    toId: String
) {
    val context = LocalContext.current

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

    if (path.isEmpty()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "No route could be found between the selected locations.",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )

            Button(
                onClick = { nav.popBackStack() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Go Back")
            }
        }
        return
    }

    // Convert to text steps
    val steps = remember(path) {
        pathToSteps(path, roomsById)
    }

    val estimatedTimeSec = remember(path) {
        TimeEstimator.estimateTime(path)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(Modifier.height(20.dp))

        Text("Directions", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f),verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("From", style = MaterialTheme.typography.labelMedium)
                    Text(
                        roomsById[fromId]?.name ?: fromId,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Column(modifier = Modifier.weight(1f),verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("To", style = MaterialTheme.typography.labelMedium)
                    Text(
                        roomsById[toId]?.name ?: toId,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Estimated Walking Time: $estimatedTimeSec sec",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(steps.size) { index ->
                ListItem(
                    headlineContent = {
                        Text("Step ${index + 1}", style = MaterialTheme.typography.labelMedium)
                    },
                    supportingContent = {
                        Text(steps[index])
                    }
                )
                if (index < steps.size - 1) {
                    Divider()
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { nav.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}