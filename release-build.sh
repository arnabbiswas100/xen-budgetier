#!/usr/bin/env bash
set -e

# Budgetier - Build & Package Script
echo "=== Step 1: Building Web Assets (www/) ==="
rm -rf www && mkdir -p www
cp index.html style.css script.js chart.umd.min.js icon-192.png icon-512.png www/ 2>/dev/null || cp index.html style.css script.js chart.umd.min.js www/
echo "Web assets copied successfully."

echo "=== Step 2: Syncing Capacitor Android ==="
export PATH="/opt/homebrew/bin:/usr/local/bin:$PATH"
export ANDROID_HOME="${ANDROID_HOME:-$HOME/Library/Android/sdk}"
npx cap sync android

echo "=== Step 3: Compiling Android APK ==="
cd android
if [ -f "my-release-key.jks" ] || [ -f "app/my-release-key.jks" ]; then
  echo "Release keystore detected. Building signed Release APK..."
  ./gradlew assembleRelease
  echo "Release APK ready at: android/app/build/outputs/apk/release/app-release.apk"
  cp app/build/outputs/apk/release/app-release.apk ../Budgetier.apk
  echo "Release APK ready at: Budgetier.apk"
else
  echo "No keystore detected. Building Debug APK for testing..."
  ./gradlew assembleDebug
  echo "Debug APK ready at: android/app/build/outputs/apk/debug/app-debug.apk"
fi

echo "=== Build Complete! ==="
