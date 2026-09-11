package com.steve.voiceassistant.service

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.telephony.TelephonyManager
import android.util.Log
import com.steve.voiceassistant.receiver.CallStateReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallMonitorService : Service() {

    private val callStateReceiver = CallStateReceiver()

    override fun onCreate() {
        super.onCreate()
        registerCallStateReceiver()
    }

    private fun registerCallStateReceiver() {
        val filter = IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        registerReceiver(callStateReceiver, filter, Context.RECEIVER_EXPORTED)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        try {
            unregisterReceiver(callStateReceiver)
        } catch (e: Exception) {
            Log.e("CallMonitorService", "Error unregistering receiver", e)
        }
    }
}
