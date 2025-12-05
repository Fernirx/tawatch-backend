#!/usr/bin/env bash
echo "============================================="
echo "          TAWATCH SETUP (Linux)              "
echo "============================================="

# 1. Check docker
if ! [ -x "$(command -v docker)" ]; then
    echo "[ERROR] Docker is not installed or not found in PATH. 
    Please install Docker and try again." >&2
    exit 1
fi

# 2. Check docker compose v2
if ! docker compose version &> /dev/null; then
    echo "[ERROR] Docker Compose V2 is not installed or not found in PATH. 
    Please install Docker Compose V2 and try again." >&2
    exit 1
fi

# 3. Check .env file
cd ../..
if [ ! -f .env ]; then
    echo "[ERROR] .env file not found! Please create one based on the .env.example file." >&2

    if [ ! -f .env.example ]; then
        echo "[ERROR] .env.example file not found. Cannot create .env file."
        echo "[ERROR] Please check repository for the .env.example template."
        exit 1
    else
        cp ".env.example" ".env"
        echo "[INFO] .env file created from .env.example template."
    fi
fi

# 4. Check application-docker.yml file
APP_PROFILE_FILE="tawatch-starter/src/main/resources/application-docker.yml"
APP_PROFILE_EXAMPLE_FILE="tawatch-starter/src/main/resources/application.example.yml"

if [ ! -f "$APP_PROFILE_FILE" ]; then
    echo "[ERROR] application-docker.yml file not found. Creating from application.example.yml..."

    if [ ! -f "$APP_PROFILE_EXAMPLE_FILE" ]; then
        echo "[ERROR] application.example.yml file not found. Cannot create application-docker.yml file."
        echo "[ERROR] Please check repository for the application.example.yml template."
        exit 1
    else
        cp "$APP_PROFILE_EXAMPLE_FILE" "$APP_PROFILE_FILE"
        echo "[INFO] application-docker.yml file created from application.example.yml template."
    fi
fi

echo "."
echo "============================================="
echo "           SETUP COMPLETED SUCCESSFULLY      "
echo "============================================="
exit 0