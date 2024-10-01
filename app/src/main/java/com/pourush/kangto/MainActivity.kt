package com.pourush.kangto

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
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
import com.pourush.kangto.hollong.ForestFireSafetyTipsScreen


class MainActivity : ComponentActivity() {

    // SOS ViewModel instance
    private val sosViewModel: SOSViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Launcher to request multiple permissions (location and SMS)
        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
            val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
            val smsPermissionGranted = permissions[Manifest.permission.SEND_SMS] ?: false

            if (!fineLocationGranted && !coarseLocationGranted) {
                Toast.makeText(this, "Location permission is required!", Toast.LENGTH_SHORT).show()
            }
            if (!smsPermissionGranted) {
                Toast.makeText(this, "SMS permission is required!", Toast.LENGTH_SHORT).show()
            }

            // Proceeding with app flow if at least COARSE_LOCATION is granted
            if (fineLocationGranted || coarseLocationGranted) {
                sosViewModel.initializeLocationService(fineLocationGranted)
            }
        }

        // Requesting location and SMS permissions
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.SEND_SMS
            )
        )

        setContent {
            ForestFireSafetyTipsScreen()
        }
    }
}

@Composable
fun KangtoApp()
{
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth())
    {
        Text(text = "Welcome To Kangto", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Text("The place for mountains",style=MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { /*TODO*/ })
        {
            Text(text = "Press to Explore")
        }
    }
}
@Composable
fun HomeScreen()
{
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth())
    {
        Text(text = "Welcome To Kangto", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Text("Inspired by the Dawn-Lit Mountain Province",style=MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { /*TODO*/ })
        {
            Text(text = "Press to Explore")
        }
    }
}


