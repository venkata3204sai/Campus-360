package com.example.campus360

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.json.JSONObject

@Composable
fun PoiListScreen(nav: NavHostController, ctx: Context) {

    val json = ctx.assets.open("rooms.json").bufferedReader().use { it.readText() }
    val rooms = Room.fromJsonArray(JSONObject(json).getJSONArray("rooms"))
    val pois = rooms.filter { it.type == "poi" }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("Points of Interest", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(pois) { poi ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { }
                        .padding(12.dp)
                ) {
                    Text(poi.name, style = MaterialTheme.typography.bodyLarge)
                    Text(poi.description ?: "", style = MaterialTheme.typography.bodySmall)
                    Text(
                        "Building: ${poi.building}, Floor: ${poi.floor}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Divider()
            }
        }
    }
}