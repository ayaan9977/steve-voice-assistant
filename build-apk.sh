#!/bin/bash
set -e
echo "Building STEVE Voice Assistant APK..."
echo "Step 1: Cleaning previous builds..."
./gradlew clean
echo "Step 2: Building release APK..."
./gradlew assembleRelease
echo "Step 3: Build complete!"
echo "APK Location: app/build/outputs/apk/release/app-release.apk"
