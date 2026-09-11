@echo off
REM Download and install STEVE Voice Assistant on Android device via ADB

echo STEVE Voice Assistant - ADB Install Script
echo ============================================
echo.

set APK_PATH=app\build\outputs\apk\release\app-release.apk

if not exist "%APK_PATH%" (
    echo ERROR: APK not found at %APK_PATH%
    echo Please run 'build-release-apk.bat' first
    pause
    exit /b 1
)

echo Checking for connected Android devices...
adb devices

echo.
echo Installing app on connected device...
adb install -r "%APK_PATH%"

if %ERRORLEVEL% equ 0 (
    echo.
    echo ^^ Installation successful!
    echo Starting app...
    adb shell am start -n com.steve.voiceassistant/.ui.MainActivity
    echo.
    echo App launched! Enjoy STEVE Voice Assistant.
) else (
    echo.
    echo ^^ Installation failed
    echo Make sure:
    echo 1. Device is connected via USB
    echo 2. USB debugging is enabled
    echo 3. ADB is installed and in PATH
    pause
    exit /b 1
)
