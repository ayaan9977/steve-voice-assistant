package com.steve.voiceassistant.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import com.steve.voiceassistant.service.VoiceAssistantService

class CallStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
        
        when (state) {
            TelephonyManager.EXTRA_STATE_RINGING,
            TelephonyManager.EXTRA_STATE_OFFHOOK -> {
                Log.d("CallStateReceiver", "Call started - Pausing voice assistant")
                pauseVoiceAssistant(context)
            }
            TelephonyManager.EXTRA_STATE_IDLE -> {
                Log.d("CallStateReceiver", "Call ended - Resuming voice assistant")
                resumeVoiceAssistant(context)
            }
        }
    }

    private fun pauseVoiceAssistant(context: Context) {
        val intent = Intent(context, VoiceAssistantService::class.java)
        context.stopService(intent)
    }

    private fun resumeVoiceAssistant(context: Context) {
        val intent = Intent(context, VoiceAssistantService::class.java)
        context.startService(intent)
    }
}
