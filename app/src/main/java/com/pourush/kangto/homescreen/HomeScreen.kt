package com.pourush.kangto.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen()
{
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth())
    {
        Text(text = "Welcome To Kangto", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Text("Inspired by the Dawn-Lit Mountain Province",style= MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { /*TODO*/ })
        {
            Text(text = "Press to Explore")
        }
        Button(onClick = { /*TODO*/ })
        {
            Text(text = "Send SOS!")
        }
        Button(onClick = { /*TODO*/ })
        {
            Text(text = "Hollong App")
        }
    }
}