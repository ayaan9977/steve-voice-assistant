# STEVE Voice Assistant - APK Build Guide

## Quick Build Instructions

### For Linux/Mac:
```bash
chmod +x build-apk.sh
./build-apk.sh
```

### For Windows:
```batch
build-apk.bat
```

### Manual Build:
```bash
./gradlew clean
./gradlew assembleRelease
```

## Output
The APK will be generated at: `app/build/outputs/apk/release/app-release.apk`

## Installation
1. Transfer the APK to your Android device
2. Enable "Unknown Sources" in Settings > Security
3. Open file manager and tap the APK to install
4. Grant all requested permissions
5. Launch the app

## Requirements
- Android 7.0+ (API 24)
- 50MB free storage
- Microphone access
- Call & SMS permissions

## Troubleshooting
If build fails:
1. Install Android SDK (API 34)
2. Install Android Build Tools (34.0.0)
3. Set ANDROID_HOME environment variable
4. Run `./gradlew --version` to verify setup
