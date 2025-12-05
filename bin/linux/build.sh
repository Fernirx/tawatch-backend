#!/usr/bin/env bash
echo "Building TAWATCH Docker image..."
cd ../..
if [ $? -ne 0 ]; then
    echo "[ERROR] Failed to navigate to project root directory."
    exit 1
fi
docker compose build
if [ $? -ne 0 ]; then
    echo "[ERROR] Docker image build failed!" >&2
    exit 1
fi
echo "Docker image built successfully."
exit 0