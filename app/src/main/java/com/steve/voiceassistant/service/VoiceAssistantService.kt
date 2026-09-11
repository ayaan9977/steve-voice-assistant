package com.steve.voiceassistant.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import com.steve.voiceassistant.data.local.PreferencesManager
import com.steve.voiceassistant.domain.repository.CommandRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class VoiceAssistantService : Service() {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    @Inject
    lateinit var commandRepository: CommandRepository

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var textToSpeech: TextToSpeech
    private val scope = CoroutineScope(Dispatchers.Main)
    private var isListening = false

    override fun onCreate() {
        super.onCreate()
        initializeSpeechRecognition()
        initializeTextToSpeech()
    }

    private fun initializeSpeechRecognition() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        startListening()
    }

    private fun initializeTextToSpeech() {
        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val voiceGender = preferencesManager.getVoiceGender()
                Log.d("VoiceAssistant", "TTS initialized with voice: $voiceGender")
            }
        }
    }

    private fun startListening() {
        val activationName = preferencesManager.getActivationName()
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        }

        isListening = true
    }

    private fun processCommand(command: String) {
        scope.launch {
            try {
                val result = commandRepository.executeCommand(command)
                speakResponse(result)
            } catch (e: Exception) {
                Log.e("VoiceAssistant", "Error processing command", e)
                speakResponse("Sorry, I couldn't understand that command")
            }
        }
    }

    private fun speakResponse(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
        textToSpeech.shutdown()
    }
}
