package com.pourush.kangto.hollong

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import androidx.compose.ui.graphics.Color as ComposeColor

@Composable
fun HollongApp() {
    val context = LocalContext.current
    // SharedPreferences to store the green score
    val sharedPrefs: SharedPreferences = context.getSharedPreferences("green_score_prefs", Context.MODE_PRIVATE)
    var greenScore by remember { mutableStateOf(sharedPrefs.getInt("greenScore", 0)) }

    // A mutable state for background color of the CardView
    var backgroundColor by remember { mutableStateOf(randomColor()) }

    // Function to increment green score and store it in SharedPreferences
    fun incrementScore() {
        greenScore++
        sharedPrefs.edit().putInt("greenScore", greenScore).apply()
        backgroundColor = randomColor() // Change background color on each increment
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display Green Score inside a CardView with dynamic background color
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = backgroundColor,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = 8.dp
        ) {
            Text(
                text = "Green Score: $greenScore",
                modifier = Modifier.padding(24.dp),
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }

        // Button to increment score
        Button(onClick = { incrementScore() })
        {
            Text(text = "Planted a Tree")
        }
        Button(onClick={})
        {
            Text("Forest Fire Safety Tips")
        }
    }
}

// Function to generate a random color
fun randomColor(): ComposeColor {
    return ComposeColor(
        Random.nextInt(0, 256),
        Random.nextInt(0, 256),
        Random.nextInt(0, 256),
        255
    )
}
