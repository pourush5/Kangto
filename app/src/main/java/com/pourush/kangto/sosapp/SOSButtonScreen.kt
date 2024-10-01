package com.pourush.kangto.sosapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pourush.kangto.R
import com.pourush.kangto.SOSViewModel


@Composable
fun SOSButtonScreen(sosViewModel: SOSViewModel = viewModel()) {
    val context = LocalContext.current
    // Permission launcher for CALL_PHONE permission
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // If permission is granted, call the number
            callEmergencyNumber(context)
        } else {
            Toast.makeText(context, "Call permission denied!", Toast.LENGTH_SHORT).show()
        }
    }
    var sosContact by remember { mutableStateOf(TextFieldValue(sosViewModel.emergencyNumber.value)) }
    var isPhoneNumberValid by remember { mutableStateOf(true) } // State to track if the phone number is valid
    //Description
    Card(backgroundColor = colorResource(R.color.teal_700),
        elevation=10.dp,
        shape= RoundedCornerShape(10.dp),
        modifier = Modifier.padding(18.dp)
    ) {
        Box(modifier = Modifier.padding(10.dp)) {
            Spacer(modifier=Modifier.height(20.dp))
            Column {
                Text(
                    "Need Help? Share your precise location to SOS contact.\n\n" +
                            "सहायता चाहिए ? अभी अपना सटीक स्थान SOS contact से साझा करें।\n\n(+91)\n",
                    fontSize = 20.sp,
                    color = Color.White
                )
                // TextField to update the SOS contact
                TextField(
                    value = sosContact,
                    onValueChange = { newValue ->
                        // Allow only digits and restrict to 10 characters
                        val filtered = newValue.text.filter { it.isDigit() }.take(10)

                        // Update the TextField value, ensuring proper cursor behavior
                        sosContact = newValue.copy(text = filtered)

                        // Update the ViewModel with the filtered phone number when valid
                        if (filtered.length == 10) {
                            sosViewModel.updateSos(TextFieldValue(filtered))  // Update only with the valid 10-digit number
                        }

                        // Check if the input is a valid 10-digit number
                        isPhoneNumberValid = filtered.length == 10
                    },
                    isError = !isPhoneNumberValid,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 18.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Blue,
                        backgroundColor = Color.White,
                        cursorColor = Color.Blue,
                        focusedIndicatorColor = colorResource(id = R.color.purple_700),
                        unfocusedIndicatorColor = Color.Blue
                    )
                )

                // Error message if the phone number is invalid
                if (!isPhoneNumberValid) {
                    Text(
                        text = "Please enter a valid 10-digit phone number.",
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }

    // State to monitor if the SOS has been triggered
    var sosTriggered by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
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
            Text(text = "Send SOS", fontSize = 30.sp)
        }

        // Displaying confirmation or error message
        if (sosTriggered) {
            Text(text = "SOS Message Sent!", fontSize = 16.sp, color = colorResource(id = R.color.teal_700))
        } else if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, fontSize = 16.sp, color = Color.Red)
        }
        Spacer(modifier=Modifier.height(10.dp))

        // Call Emergency Button
        Button(
            onClick = {
                // Check if the CALL_PHONE permission is granted
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) ==
                    PackageManager.PERMISSION_GRANTED) {
                    callEmergencyNumber(context)
                } else {
                    // Request permission if not granted
                    requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Call Disaster Emergency (1070)",fontSize = 20.sp)
        }
    }
}

private fun callEmergencyNumber(context: Context) {
    val callIntent = Intent(Intent.ACTION_CALL).apply {
        data = Uri.parse("tel:1070")
    }
    try {
        context.startActivity(callIntent)
    } catch (e: SecurityException) {
        Toast.makeText(context, "Call permission is not granted!", Toast.LENGTH_SHORT).show()
    }
}