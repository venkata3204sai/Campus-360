package com.example.campus360.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.campus360.R

@Composable
fun HomeScreen(nav: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = painterResource(R.drawable.campus360_transparent),
            contentDescription = "App Logo",
            modifier = Modifier.size(250.dp)
        )

        Text(
            "CAMPUS360",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary)

        Spacer(Modifier.height(8.dp))

        Text(
            "Navigate Smarter Every Day",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary)

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { nav.navigate("find") },
            modifier = Modifier.fillMaxWidth()) {
            Text("Find & Go")
        }
    }
}