package com.steve.voiceassistant.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.steve.voiceassistant.data.local.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _isListening = MutableLiveData<Boolean>(false)
    val isListening: LiveData<Boolean> = _isListening

    private val _activationName = MutableLiveData<String>()
    val activationName: LiveData<String> = _activationName

    private val _commandResult = MutableLiveData<String>()
    val commandResult: LiveData<String> = _commandResult

    init {
        _activationName.value = preferencesManager.getActivationName()
    }

    fun toggleListening() {
        _isListening.value = !(_isListening.value ?: false)
    }

    fun isBiometricSetup(): Boolean = preferencesManager.isBiometricSetup()

    fun setActivationName(name: String) {
        preferencesManager.setActivationName(name)
        _activationName.value = name
    }

    fun setVoiceGender(gender: String) {
        preferencesManager.setVoiceGender(gender)
    }
}
