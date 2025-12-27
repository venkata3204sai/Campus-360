package com.example.campus360

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.navigation.NavHostController
import android.content.Context
import androidx.compose.ui.unit.dp
import org.json.JSONObject


private val LOCATION_TYPES = listOf(
    "classroom" to "Classrooms",
    "lab" to "Labs",
    "office" to "Offices",
    "poi" to "POIs"
)

enum class SelectionMode {
    DESTINATION,
    START
}

@Composable
fun FindAndGoScreen(nav: NavHostController, context: Context) {

    var searchQuery by remember { mutableStateOf("") }

    var selectionMode by remember { mutableStateOf(SelectionMode.DESTINATION) }

    var selectedDestinationId by remember { mutableStateOf<String?>(null) }
    var selectedStartId by remember { mutableStateOf<String?>(null) }


    val selectedFilters = remember { mutableStateOf(setOf<String>()) }

    // Load rooms + POIs (reuse Room model)
    val rooms = remember {
        val json = context.assets.open("rooms.json")
            .bufferedReader().use { it.readText() }
        Room.fromJsonArray(JSONObject(json).getJSONArray("rooms"))
    }

    val filteredResults = rooms.filter { room ->
        val matchesSearch =
            searchQuery.isBlank() ||
                    room.id.contains(searchQuery, true) ||
                    room.name.contains(searchQuery, true)

        val matchesFilter =
            selectedFilters.value.isEmpty() ||
                    selectedFilters.value.contains(room.type)

        matchesSearch && matchesFilter
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("Find & Go", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search room or POI") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        // FILTER CHIPS (Classroom, Lab, Office, POI)
        FilterRow(
            selectedFilters = selectedFilters.value,
            onToggle = { type ->
                selectedFilters.value =
                    if (selectedFilters.value.contains(type))
                        selectedFilters.value - type
                    else
                        selectedFilters.value + type
            }
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = when {
                selectionMode == SelectionMode.START && selectedDestinationId == null ->
                    "Select a destination first"
                selectionMode == SelectionMode.START ->
                    "Tap a location to set START"
                else ->
                    "Tap a location to set DESTINATION"
            },
            color = MaterialTheme.colorScheme.primary
        )

        if (filteredResults.isEmpty()) {
            Text(
                "No locations found matching your search.",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(Modifier.height(8.dp))

        // RESULTS

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(filteredResults) { room ->
                ListItem(
                    headlineContent = { Text(room.id) },
                    supportingContent = {
                        Text(
                            "${room.type.uppercase()} • ${room.building} • Floor ${room.floor}"
                        )
                    },
                    modifier = Modifier.clickable {
                        if (selectionMode == SelectionMode.DESTINATION) {
                            selectedDestinationId = room.id
                        } else {
                            selectedStartId = room.id
                            selectionMode = SelectionMode.DESTINATION
                        }
                    }

                )
                Divider()
            }
        }


        Spacer(Modifier.height(12.dp))

        if (selectedDestinationId != null) {
            Button(
                onClick = { selectionMode = SelectionMode.START },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Set Start Location")
            }
        }

        Button(
            onClick = {
                nav.navigate("directions/$selectedStartId/$selectedDestinationId")
            },
            enabled = selectedStartId != null && selectedDestinationId != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show Directions")
        }
    }
}


@Composable
fun FilterRow(
    selectedFilters: Set<String>,
    onToggle: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LOCATION_TYPES.forEach { (type, label) ->
            FilterChip(
                selected = selectedFilters.contains(type),
                onClick = { onToggle(type) },
                label = { Text(label) }
            )
        }
    }
}
