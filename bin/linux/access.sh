#!/usr/bin/env bash
cd ../..
if [ $? -ne 0 ]; then
    echo "[ERROR] Failed to navigate to project root directory."
    exit 1
fi
service_name=${1:-app}
if [ "$service_name" == "app" ]; then
    docker compose exec app bash
elif [ "$service_name" == "mysql" ]; then
    docker compose exec mysql mysql -u root -p
else
    echo "[ERROR] Unknown service name: $service_name" >&2
    exit 1
fi
if [ $? -ne 0 ]; then
    echo "[ERROR] Failed to access TAWATCH $service_name container." >&2
    exit 1
fi
echo "Accessed TAWATCH $service_name container successfully."
exit 0