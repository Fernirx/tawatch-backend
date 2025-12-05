@echo off
echo "Starting TAWATCH Application..."
cd ../.. || exit /b 1
docker compose up --force-recreate -d
if %errorlevel% neq 0 (
    echo [ERROR] Failed to start TAWATCH application.
    pause
    exit /b 1
)
echo "TAWATCH application started successfully."
pause
exit /b 0