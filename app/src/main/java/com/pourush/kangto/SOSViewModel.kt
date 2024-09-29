package com.pourush.kangto

import android.app.Application
import android.location.Location
import android.telephony.SmsManager
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.launch

class SOSViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // Store whether fine location permission was granted
    private var isFineLocationGranted: Boolean = false

    // Initialize location services based on permissions
    fun initializeLocationService(fineLocationGranted: Boolean) {
        isFineLocationGranted = fineLocationGranted
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    // Trigger SOS message sending
    fun triggerSOS(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // Get current location (either FINE or COARSE based on permission)
                getCurrentLocation { location ->
                    if (location != null) {
                        sendSOSMessage(location, onSuccess, onFailure)
                    } else {
                        onFailure("Failed to get location")
                    }
                }
            } catch (e: Exception) {
                onFailure("Error triggering SOS: ${e.message}")
            }
        }
    }

    // Get current location based on granted permissions
    private fun getCurrentLocation(callback: (Location?) -> Unit) {
        try {
            val locationTask: Task<Location> = if (isFineLocationGranted) {
                // Access precise location (GPS)
                fusedLocationClient.lastLocation
            } else {
                // Access approximate location (network, Wi-Fi)
                fusedLocationClient.lastLocation
            }

            locationTask.addOnSuccessListener { location ->
                if (location != null) {
                    callback(location)
                } else {
                    callback(null)
                }
            }.addOnFailureListener {
                callback(null)
            }
        } catch (e: SecurityException) {
            callback(null)
        }
    }

    // Send the SOS message
    private fun sendSOSMessage(location: Location, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            val emergencyNumber = "9076787407" // Replace with actual emergency contact number
            val message = "SOS! I'm at Latitude: ${location.latitude}, Longitude: ${location.longitude}"

            // Send SMS
            smsManager.sendTextMessage(emergencyNumber, null, message, null, null)

            // Show success message
            Toast.makeText(context, "SOS Message Sent!", Toast.LENGTH_SHORT).show()
            onSuccess()
        } catch (e: Exception) {
            onFailure("Failed to send SOS: ${e.message}")
        }
    }
}