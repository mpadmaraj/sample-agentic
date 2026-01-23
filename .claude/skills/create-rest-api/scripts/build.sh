#!/usr/bin/env bash
set -euo pipefail

echo "================================="
echo " Running build"
echo "================================="

if [ ! -f "pom.xml" ]; then
  echo "ERROR: pom.xml not found. Are you in the project root?"
  exit 1
fi

echo "Running Maven compile..."
mvn clean compile

echo "Build completed successfully "
