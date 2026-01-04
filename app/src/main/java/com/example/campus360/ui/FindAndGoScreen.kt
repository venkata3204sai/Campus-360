package com.example.campus360.ui

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.campus360.data.model.Room
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

    var qrErrorMessage by remember { mutableStateOf<String?>(null) }

    val rooms = remember {
        val json = context.assets.open("rooms.json")
            .bufferedReader().use { it.readText() }
        Room.fromJsonArray(JSONObject(json).getJSONArray("rooms"))
    }

    val qrResult = nav.currentBackStackEntry
        ?.savedStateHandle
        ?.get<String>("qr_result")

    LaunchedEffect(qrResult) {
        qrResult?.let { scannedId ->
            val validIds = rooms.map { it.id }.toSet()

            if (validIds.contains(scannedId)) {
                selectedStartId = scannedId
                qrErrorMessage = null
            } else {
                qrErrorMessage = "Invalid QR code. This location is not recognised"
            }

            nav.currentBackStackEntry
                ?.savedStateHandle
                ?.remove<String>("qr_result")
        }
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

    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(Modifier.height(20.dp))

        Text("Find & Go", style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary)

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search room or POI") },
            modifier = Modifier.fillMaxWidth().testTag("searchField"),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary
            ))

        Spacer(Modifier.height(8.dp))

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

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(filteredResults) { room ->
                ListItem(
                    headlineContent = { Text(room.name) },
                    supportingContent = {
                        Column {
                            Text(
                                "${room.type.uppercase()} • ${room.building} • Floor ${room.floor}"
                            )
                            if (room.description.isNotBlank()) {
                                Text(
                                    text = room.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    },
                    modifier = Modifier.clickable {
                        if (selectionMode == SelectionMode.DESTINATION) {
                            selectedDestinationId = room.id
                        } else {
                            selectedStartId = room.id
                            qrErrorMessage = null
                            selectionMode = SelectionMode.DESTINATION
                        }
                    }

                )
                if (filteredResults.indexOf(room) < filteredResults.size - 1) {
                    Divider()
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        if (selectedDestinationId != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Destination: $selectedDestinationId")
                TextButton(onClick = {
                    selectedDestinationId = null
                    selectedStartId = null
                    selectionMode = SelectionMode.DESTINATION
                }) {
                    Text("Clear")
                }
            }
        }

        if (selectedStartId != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Start: $selectedStartId")
                TextButton(onClick = {
                    selectedStartId = null
                }) {
                    Text("Clear")
                }
            }
        }

        if (selectedStartId != null && selectedDestinationId != null) {
            OutlinedButton(
                onClick = {
                    val temp = selectedStartId
                    selectedStartId = selectedDestinationId
                    selectedDestinationId = temp
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Swap Start and Destination")
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

        if (qrErrorMessage != null) {
            Text(
                qrErrorMessage!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        OutlinedButton(
            onClick = {
                nav.navigate("qr")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Scan QR")
        }

        Spacer(Modifier.height(12.dp))

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
        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
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
