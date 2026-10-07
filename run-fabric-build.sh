#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"

JDK25="/c/Users/Joanny/.gradle/jdks/eclipse_adoptium-25-amd64-windows.2"
if [[ ! -x "$JDK25/bin/java" ]]; then
  echo "Java 25 not found at $JDK25"
  exit 1
fi
export JAVA_HOME="$JDK25"
export PATH="$JAVA_HOME/bin:$PATH"

./gradlew :fabric:build --no-daemon \
  -Dorg.gradle.java.installations.auto-detect=true \
  -Dorg.gradle.java.installations.paths="$JAVA_HOME"

echo "EXIT=$?"
ls -la fabric/build/libs || true
