package com.pourush.kangto

import android.content.Context
import android.telephony.SmsManager
import android.widget.Toast

object SMSUtils {

    fun sendSMS(context: Context, phoneNumber: String, message: String) {
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(context, "SOS message sent!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Failed to send SOS message.", Toast.LENGTH_SHORT).show()
        }
    }
}