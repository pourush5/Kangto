package com.pourush.kangto

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

object LocationUtils {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    suspend fun getLastKnownLocation(context: Context): Location? {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        return if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.await()
        } else {
            null
        }
    }
}
