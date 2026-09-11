package com.steve.voiceassistant.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "steve_preferences",
        Context.MODE_PRIVATE
    )

    fun setActivationName(name: String) {
        sharedPreferences.edit().putString("activation_name", name).apply()
    }

    fun getActivationName(): String {
        return sharedPreferences.getString("activation_name", "STEVE") ?: "STEVE"
    }

    fun setVoiceGender(gender: String) {
        sharedPreferences.edit().putString("voice_gender", gender).apply()
    }

    fun getVoiceGender(): String {
        return sharedPreferences.getString("voice_gender", "male") ?: "male"
    }

    fun setBiometricSetup(isSetup: Boolean) {
        sharedPreferences.edit().putBoolean("biometric_setup", isSetup).apply()
    }

    fun isBiometricSetup(): Boolean {
        return sharedPreferences.getBoolean("biometric_setup", false)
    }
}
