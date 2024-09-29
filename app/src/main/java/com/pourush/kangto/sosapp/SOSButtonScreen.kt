package com.pourush.kangto.sosapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pourush.kangto.SOSViewModel

@Composable
fun SOSButtonScreen(sosViewModel: SOSViewModel = viewModel()) {
    // State to monitor if the SOS has been triggered
    var sosTriggered by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // SOS Button
        Button(
            onClick = {
                // Triggering SOS action from ViewModel
                sosViewModel.triggerSOS(
                    onSuccess = {
                        sosTriggered = true // Successfully sent SOS message
                    },
                    onFailure = { error ->
                        errorMessage = error // Failed to send SOS
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Send SOS", fontSize = 18.sp)
        }

        // Displaying confirmation or error message
        if (sosTriggered) {
            Text(text = "SOS Message Sent!", fontSize = 16.sp, color = Color.Green)
        } else if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, fontSize = 16.sp, color = Color.Red)
        }
    }
}