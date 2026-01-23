#!/usr/bin/env bash
set -euo pipefail

echo "Running tests..."
mvn test

echo "All tests passed successfully "
