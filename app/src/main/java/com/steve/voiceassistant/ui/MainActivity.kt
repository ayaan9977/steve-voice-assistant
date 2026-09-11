package com.steve.voiceassistant.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.steve.voiceassistant.R
import com.steve.voiceassistant.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var statusText: TextView
    private lateinit var micButton: ImageButton
    private lateinit var settingsButton: ImageButton
    private lateinit var activationNameText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initViews()
        setupListeners()
        checkBiometricSetup()
        observeViewModelData()
    }

    private fun initViews() {
        statusText = findViewById(R.id.statusText)
        micButton = findViewById(R.id.micButton)
        settingsButton = findViewById(R.id.settingsButton)
        activationNameText = findViewById(R.id.activationNameText)
    }

    private fun setupListeners() {
        micButton.setOnClickListener {
            viewModel.toggleListening()
        }

        settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    private fun checkBiometricSetup() {
        if (!viewModel.isBiometricSetup()) {
            startActivity(Intent(this, BiometricSetupActivity::class.java))
        }
    }

    private fun observeViewModelData() {
        viewModel.isListening.observe(this) { isListening ->
            statusText.text = if (isListening) "Listening..." else "Ready"
            micButton.isEnabled = true
        }

        viewModel.activationName.observe(this) { name ->
            activationNameText.text = "Activation: $name"
        }

        viewModel.commandResult.observe(this) { result ->
            statusText.text = result
        }
    }
}
