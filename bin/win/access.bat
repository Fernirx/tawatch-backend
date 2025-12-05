@echo off
cd ../.. || exit /b 1
set "service_name=%1"
if not defined service_name set "service_name=app"
if "%service_name%"=="app" (
    docker compose exec app /bin/bash
) else if "%service_name%"=="mysql" (
    docker compose exec mysql mysql -u root -p
) else (
    echo [ERROR] Unknown service name: %service_name%
    pause
    exit /b 1
)

echo "Accessed TAWATCH %service_name% container successfully."
pause
exit /b 0