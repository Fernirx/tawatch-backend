#!/usr/bin/env bash
echo "Stopping TAWATCH Application..."
cd ../..
if [ $? -ne 0 ]; then
    echo "[ERROR] Failed to navigate to project root directory."
    exit 1
fi
docker compose down
if [ $? -ne 0 ]; then
    echo "[ERROR] Failed to stop TAWATCH Application." >&2
    exit 1
fi
echo "TAWATCH Application stopped successfully."
exit 0