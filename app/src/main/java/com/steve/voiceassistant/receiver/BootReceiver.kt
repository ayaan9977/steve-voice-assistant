package com.steve.voiceassistant.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.steve.voiceassistant.service.VoiceAssistantService

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Log.d("BootReceiver", "Device booted - Starting voice assistant service")
            val serviceIntent = Intent(context, VoiceAssistantService::class.java)
            context.startService(serviceIntent)
        }
    }
}
