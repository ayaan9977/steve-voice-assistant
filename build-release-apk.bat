@echo off
REM This script builds the APK and prepares it for distribution on Windows

echo.
echo ════════════════════════════════════════════════
echo   STEVE Voice Assistant - APK Builder
echo ════════════════════════════════════════════════
echo.

REM Check if gradlew exists
if not exist "gradlew.bat" (
    echo ERROR: gradlew.bat not found. Please run this from the project root.
    pause
    exit /b 1
)

echo [1/4] Cleaning previous builds...
call gradlew clean

echo.
echo [2/4] Building release APK...
call gradlew assembleRelease

echo.
echo [3/4] Verifying APK...
set APK_PATH=app\build\outputs\apk\release\app-release.apk

if exist "%APK_PATH%" (
    echo ✓ APK built successfully!
    echo   File: %APK_PATH%
    echo.
    echo [4/4] Build Summary:
    echo ════════════════════════════════════════════════
    echo   App Name: STEVE Voice Assistant
    echo   Version: 1.0.0
    echo   Min API: 24 (Android 7.0)
    echo   Target API: 34 (Android 14)
    echo   APK Path: %APK_PATH%
    echo ════════════════════════════════════════════════
    echo.
    echo Next steps:
    echo 1. Transfer APK to your Android device
    echo 2. Enable 'Install from Unknown Sources' in Settings
    echo 3. Tap the APK file to install
    echo 4. Grant all requested permissions
    echo.
    pause
    exit /b 0
) else (
    echo ERROR: APK build failed. Check the console output above.
    pause
    exit /b 1
)
