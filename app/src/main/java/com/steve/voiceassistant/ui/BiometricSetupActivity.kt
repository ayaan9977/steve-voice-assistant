package com.steve.voiceassistant.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.steve.voiceassistant.R
import com.steve.voiceassistant.data.local.PreferencesManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BiometricSetupActivity : AppCompatActivity() {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometric_setup)

        val setupButton: Button = findViewById(R.id.setupBiometricButton)
        val skipButton: Button = findViewById(R.id.skipButton)

        setupBiometric()

        setupButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }

        skipButton.setOnClickListener {
            navigateToMain()
        }
    }

    private fun setupBiometric() {
        biometricPrompt = BiometricPrompt(
            this,
            ContextCompat.getMainExecutor(this),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    preferencesManager.setBiometricSetup(true)
                    Toast.makeText(this@BiometricSetupActivity, "Biometric setup complete", Toast.LENGTH_SHORT).show()
                    navigateToMain()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(this@BiometricSetupActivity, "Auth error: $errString", Toast.LENGTH_SHORT).show()
                }
            }
        )

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Setup for Steve")
            .setSubtitle("Scan your fingerprint to enable voice assistant")
            .setNegativeButtonText("Cancel")
            .build()
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
