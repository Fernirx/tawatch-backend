#!/usr/bin/env bash
echo "Starting TAWATCH Application..."
cd ../..
if [ $? -ne 0 ]; then
    echo "[ERROR] Failed to navigate to project root directory."
    exit 1
fi
docker compose up --force-recreate -d
if [ $? -ne 0 ]; then
    echo "[ERROR] Failed to start TAWATCH application."
    exit 1
fi
echo "TAWATCH application started successfully."
exit 0