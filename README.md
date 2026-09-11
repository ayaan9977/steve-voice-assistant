# STEVE Voice Assistant

A comprehensive Android voice assistant application with advanced features including biometric authentication, task automation, and smart device control.

## Features

✨ **Voice Recognition & Response**
- Real-time speech recognition
- Natural language processing
- Text-to-speech responses with customizable voice (Male/Female)

🔐 **Security**
- Fingerprint biometric authentication
- Secure permission management
- Encrypted preference storage

📱 **Smart Tasks**
- Make phone calls
- Send SMS messages
- Transfer payments via JazzCash/EasyPaisa
- Open installed applications (Instagram, TikTok, etc.)

⚙️ **Customization**
- Customize activation name (default: "STEVE")
- Choose voice gender (Male/Female)
- Personalized settings and preferences

🔔 **Call Management**
- Automatically pauses when phone call starts
- Resumes after call ends
- Background service operation

🚀 **Auto-Start**
- Starts automatically on device boot
- Persistent background service

## Installation

1. Clone the repository
2. Open in Android Studio
3. Build the APK
4. Install on Android device (API 24+)

## Permissions Required

- RECORD_AUDIO - For voice recognition
- CALL_PHONE - To make calls
- READ_CONTACTS - To access contacts
- SEND_SMS - To send messages
- MODIFY_AUDIO_SETTINGS - To control audio
- READ_PHONE_STATE - To monitor calls
- USE_BIOMETRIC - For fingerprint authentication
- INTERNET - For API calls

## Usage

1. Launch the app
2. Set up biometric authentication
3. Customize activation name and voice in settings
4. Say "STEVE" or your custom activation name
5. Speak your command

## Supported Commands

- "Call [contact name/number]"
- "Send payment to [person] via [JazzCash/EasyPaisa]"
- "Open [app name]"
- "Send message"

## Technology Stack

- Kotlin
- Android Architecture Components (MVVM)
- Dagger-Hilt for Dependency Injection
- Room Database
- Android Speech Recognition API
- Text-to-Speech Engine
- Biometric Authentication
- Retrofit for API calls

## Build Instructions

```bash
./gradlew build
./gradlew assembleRelease  # For APK
```

## Project Structure

```
app/src/main/
├── java/com/steve/voiceassistant/
│   ├── ui/                 # Activities and UI
│   ├── service/            # Background services
│   ├── receiver/           # Broadcast receivers
│   ├── viewmodel/          # MVVM ViewModels
│   ├── data/               # Data layer
│   ├── domain/             # Domain layer
│   └── di/                 # Dependency injection
├── res/                    # Resources
└── AndroidManifest.xml
```

## Contributing

Contributions are welcome! Please follow the Android development best practices.

## License

MIT License

## Support

For issues and feature requests, please use the GitHub issues tracker.
