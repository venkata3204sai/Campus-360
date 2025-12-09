package com.example.campus360

import android.content.Context
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.json.JSONObject

@Composable
fun SearchScreen(nav: NavHostController, ctx: Context) {

    val json = remember {
        ctx.assets.open("rooms.json").bufferedReader().use { it.readText() }
    }

    val rooms = remember {
        Room.fromJsonArray(JSONObject(json).getJSONArray("rooms"))
    }

    var query by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("All") }

    val types = listOf("All", "classroom", "lab", "office", "poi")

    val results = rooms.filter {
        val matchesText =
            it.name.lowercase().contains(query.lowercase()) ||
                    it.id.lowercase().contains(query.lowercase())

        val matchesType =
            selectedType == "All" || it.type == selectedType

        matchesText && matchesType
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("Search Rooms", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Enter room name e.g. G210") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            types.forEach { type ->
                val isSelected = type == selectedType
                Button(
                    onClick = { selectedType = type },
                    colors = if (isSelected)
                        ButtonDefaults.buttonColors()
                    else
                        ButtonDefaults.outlinedButtonColors()
                ) {
                    Text(type)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (query.isNotEmpty() && results.isEmpty()) {
            Text("No results found", color = MaterialTheme.colorScheme.error)
        }

        LazyColumn {
            items(results) { room ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { }
                        .padding(12.dp)
                ) {
                    Text(room.name, style = MaterialTheme.typography.bodyLarge)
                    Text(
                        "Building: ${room.building}, Floor: ${room.floor}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text("Type: ${room.type}", style = MaterialTheme.typography.bodySmall)
                }
                Divider()
            }
        }
    }
}
