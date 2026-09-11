package com.steve.voiceassistant.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class PermissionHelper(private val context: Context) {
    
    companion object {
        val REQUIRED_PERMISSIONS = arrayOf(
            android.Manifest.permission.RECORD_AUDIO,
            android.Manifest.permission.CALL_PHONE,
            android.Manifest.permission.READ_CONTACTS,
            android.Manifest.permission.SEND_SMS,
            android.Manifest.permission.MODIFY_AUDIO_SETTINGS,
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.USE_BIOMETRIC
        )
    }
    
    fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
    
    fun hasAllPermissions(): Boolean {
        return REQUIRED_PERMISSIONS.all { hasPermission(it) }
    }
    
    fun getMissingPermissions(): List<String> {
        return REQUIRED_PERMISSIONS.filter { !hasPermission(it) }
    }
}
