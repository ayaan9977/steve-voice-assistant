package com.steve.voiceassistant.data.repository

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import com.steve.voiceassistant.domain.repository.CommandRepository
import javax.inject.Inject

class CommandRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CommandRepository {

    override suspend fun executeCommand(command: String): String {
        return when {
            command.contains("call", ignoreCase = true) -> handleCallCommand(command)
            command.contains("payment", ignoreCase = true) || 
            command.contains("jazzcash", ignoreCase = true) ||
            command.contains("easypaisa", ignoreCase = true) -> handlePaymentCommand(command)
            command.contains("open", ignoreCase = true) -> handleOpenAppCommand(command)
            command.contains("message", ignoreCase = true) || 
            command.contains("sms", ignoreCase = true) -> handleSmsCommand(command)
            else -> "Command not recognized. Try: call, payment, open app, or send message"
        }
    }

    private suspend fun handleCallCommand(command: String): String {
        return try {
            val intent = Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:+923001234567") // Example phone number
            }
            context.startActivity(intent)
            "Calling..."
        } catch (e: Exception) {
            "Unable to make call: ${e.message}"
        }
    }

    private suspend fun handlePaymentCommand(command: String): String {
        return try {
            val paymentApp = when {
                command.contains("jazzcash", ignoreCase = true) -> "com.jazzworld.jmoney"
                command.contains("easypaisa", ignoreCase = true) -> "pk.com.easypaisa.easypaisa"
                else -> null
            }
            
            if (paymentApp != null && isAppInstalled(paymentApp)) {
                openApp(paymentApp)
                "Opening payment app. Please complete the transaction"
            } else {
                "Payment app not installed"
            }
        } catch (e: Exception) {
            "Payment error: ${e.message}"
        }
    }

    private suspend fun handleOpenAppCommand(command: String): String {
        return try {
            val appName = extractAppName(command)
            val packageName = mapAppNameToPackage(appName)
            
            if (openApp(packageName)) {
                "Opening $appName"
            } else {
                "App not found"
            }
        } catch (e: Exception) {
            "Error opening app: ${e.message}"
        }
    }

    private suspend fun handleSmsCommand(command: String): String {
        return try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:+923001234567")
                putExtra("sms_body", "Message body")
            }
            context.startActivity(intent)
            "SMS application opened"
        } catch (e: Exception) {
            "Error sending SMS: ${e.message}"
        }
    }

    override suspend fun getAvailableApps(): List<String> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val packageManager = context.packageManager
        return packageManager.queryIntentActivities(intent, 0).map { it.activityInfo.packageName }
    }

    override suspend fun callPerson(phoneNumber: String): Boolean {
        return try {
            val intent = Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun sendPayment(recipient: String, amount: Double, method: String): Boolean {
        return try {
            val paymentApp = when (method.lowercase()) {
                "jazzcash" -> "com.jazzworld.jmoney"
                "easypaisa" -> "pk.com.easypaisa.easypaisa"
                else -> return false
            }
            isAppInstalled(paymentApp) && openApp(paymentApp)
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun openApp(appPackageName: String): Boolean {
        return try {
            val intent = context.packageManager.getLaunchIntentForPackage(appPackageName)
            if (intent != null) {
                context.startActivity(intent)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    private fun isAppInstalled(packageName: String): Boolean {
        return try {
            context.packageManager.getApplicationInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun extractAppName(command: String): String {
        return command.replace("open", "", ignoreCase = true).trim()
    }

    private fun mapAppNameToPackage(appName: String): String {
        return when (appName.lowercase()) {
            "instagram" -> "com.instagram.android"
            "tiktok" -> "com.ss.android.ugc.tiktok"
            "whatsapp" -> "com.whatsapp"
            "facebook" -> "com.facebook.katana"
            "youtube" -> "com.google.android.youtube"
            "gmail" -> "com.google.android.gm"
            "maps" -> "com.google.android.apps.maps"
            "chrome" -> "com.android.chrome"
            else -> appName
        }
    }
}
