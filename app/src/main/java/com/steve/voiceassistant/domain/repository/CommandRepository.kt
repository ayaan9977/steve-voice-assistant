package com.steve.voiceassistant.domain.repository

interface CommandRepository {
    suspend fun executeCommand(command: String): String
    suspend fun getAvailableApps(): List<String>
    suspend fun callPerson(phoneNumber: String): Boolean
    suspend fun sendPayment(recipient: String, amount: Double, method: String): Boolean
    suspend fun openApp(appPackageName: String): Boolean
}
