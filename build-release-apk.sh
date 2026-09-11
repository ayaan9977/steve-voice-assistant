#!/bin/bash

# This script builds the APK and prepares it for distribution

echo "════════════════════════════════════════════════"
echo "  STEVE Voice Assistant - APK Builder"
echo "════════════════════════════════════════════════"
echo ""

# Check if gradlew exists
if [ ! -f "./gradlew" ]; then
    echo "ERROR: gradlew not found. Please run this from the project root."
    exit 1
fi

echo "[1/4] Cleaning previous builds..."
./gradlew clean

echo ""
echo "[2/4] Building release APK..."
./gradlew assembleRelease

echo ""
echo "[3/4] Verifying APK..."
APK_PATH="app/build/outputs/apk/release/app-release.apk"

if [ -f "$APK_PATH" ]; then
    APK_SIZE=$(du -h "$APK_PATH" | cut -f1)
    echo "✓ APK built successfully!"
    echo "  File: $APK_PATH"
    echo "  Size: $APK_SIZE"
    echo ""
    echo "[4/4] Build Summary:"
    echo "════════════════════════════════════════════════"
    echo "  App Name: STEVE Voice Assistant"
    echo "  Version: 1.0.0"
    echo "  Min API: 24 (Android 7.0)"
    echo "  Target API: 34 (Android 14)"
    echo "  APK Path: $APK_PATH"
    echo "════════════════════════════════════════════════"
    echo ""
    echo "Next steps:"
    echo "1. Transfer APK to your Android device"
    echo "2. Enable 'Install from Unknown Sources' in Settings"
    echo "3. Tap the APK file to install"
    echo "4. Grant all requested permissions"
    echo ""
    exit 0
else
    echo "ERROR: APK build failed. Check the console output above."
    exit 1
fi
