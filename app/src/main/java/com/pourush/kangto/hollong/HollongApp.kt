package com.pourush.kangto.hollong

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pourush.kangto.R
import com.pourush.kangto.Screen
import kotlin.random.Random
import androidx.compose.ui.graphics.Color as ComposeColor

@Composable
fun HollongApp(navController: NavController) {
    val context = LocalContext.current
    // SharedPreferences to store the hollong score
    val sharedPrefs: SharedPreferences = context.getSharedPreferences("hollong_score_prefs", Context.MODE_PRIVATE)
    var greenScore by remember { mutableStateOf(sharedPrefs.getInt("hollongScore", 0)) }

    // A mutable state for background color of the CardView
    var backgroundColor by remember { mutableStateOf(randomColor()) }

    // Function to increment hollong score and store it in SharedPreferences
    fun incrementScore() {
        greenScore++
        sharedPrefs.edit().putInt("hollongScore", greenScore).apply()
        backgroundColor = randomColor() // Change background color on each increment
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).background(color=backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Description of Buttons to increment Hollong Score
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.White,
            modifier = Modifier
                .padding(5.dp),
            elevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.tree_24dp),
                    contentDescription = "Planted a Tree",
                    tint = colorResource(id = R.color.forest_essence)
                )
                Text("Tap this below if planted a Tree! | वृक्षारोपण करने पर नीचे इसे टैप करें।")

            }
        }
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.White,
            modifier = Modifier
                .padding(5.dp),
            elevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.report_24dp),
                    contentDescription = "Reported Forest Danger",
                    tint = colorResource(id = R.color.report_danger)
                )
                Text("Reported a forest danger (invasive species,forest fire,water pollution,etc.) to concerned authorities? Tap this below! | वन संकट (आक्रामक उपजाति, दावानल, जल प्रदूषण, इत्यादि) सही जगह सूचित करने पर नीचे इसे टैप करें। ")

            }
        }
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.White,
            modifier = Modifier
                .padding(5.dp),
            elevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.recycle_24dp),
                    contentDescription = "Recycled",
                    tint = colorResource(id = R.color.teal_700)
                )
                Text("Tap this below if recycled an item! | किसी पुनर्चक्रण योग्य वस्तु का पुनः उपयोग करने पर नीचे इसे टैप करें। ")

            }

        }
        // Display Hollong Score inside a CardView with dynamic background color
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.White,
            modifier = Modifier
                .padding(16.dp),
            elevation = 8.dp
        ) {

            Card(
                shape = RoundedCornerShape(16.dp),
                backgroundColor = colorResource(id = R.color.forest_essence),
                modifier = Modifier
                    .padding(25.dp),
                elevation = 8.dp
            ){
            Text(
                text = "Hollong Score: $greenScore",
                modifier = Modifier.padding(24.dp),
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            }
        }
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.White,
            modifier = Modifier
                .padding(25.dp),
            elevation = 8.dp
        ){
        //Row containing actionables to increment Hollong Score
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { incrementScore() },
            )
            {
                Icon(
                    modifier=Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.tree_24dp),
                    contentDescription = "Planted a Tree",
                    tint = colorResource(id = R.color.forest_essence)
                )
            }
            IconButton(
                onClick = { incrementScore() },
            )
            {
                Icon(
                    modifier=Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.report_24dp),
                    contentDescription = "Reported Forest Danger",
                    tint = colorResource(id = R.color.report_danger)
                )
            }
            IconButton(
                onClick = { incrementScore() },
            )
            {
                Icon(
                    modifier=Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.recycle_24dp),
                    contentDescription = "Recycled",
                    tint = colorResource(id = R.color.teal_700)
                )
            }
        }
        }
        Button(onClick={navController.navigate(Screen.ForestFireTipScreen.route)})
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
