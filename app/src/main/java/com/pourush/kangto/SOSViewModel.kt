package com.pourush.kangto

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

class SOSViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SOSAppPreferences", Context.MODE_PRIVATE)

    // Mutable state for the emergency number
    var emergencyNumber = mutableStateOf("")

    init {
        // Load the persisted SOS contact from SharedPreferences when the ViewModel is created
        emergencyNumber.value = sharedPreferences.getString("sosContact", "") ?: ""
    }

    // Method to update the SOS contact and persist it using SharedPreferences
    fun updateSos(sosContact: TextFieldValue) {
        emergencyNumber.value = sosContact.text
        sharedPreferences.edit().putString("sosContact", sosContact.text).apply() // Persist the contact
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
            val locationTask = fusedLocationClient.lastLocation

            locationTask.addOnSuccessListener { location ->
                callback(location)
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
            val message = "KangtoApp: SOS! I'm at Latitude: ${location.latitude}, Longitude: ${location.longitude}"

            // Send SMS using the persisted emergency number
            val emergencyContact = emergencyNumber.value
            if (emergencyContact.isNotEmpty()) {
                smsManager.sendTextMessage(emergencyContact, null, message, null, null)

                // Show success message
                Toast.makeText(context, "SOS Message Sent!", Toast.LENGTH_SHORT).show()
                onSuccess()
            } else {
                onFailure("Emergency contact number is empty!")
            }
        } catch (e: Exception) {
            onFailure("Failed to send SOS: ${e.message}")
        }
    }

    // Initialize location services based on permissions
    fun initializeLocationService(fineLocationGranted: @JvmSuppressWildcards Boolean) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }
}