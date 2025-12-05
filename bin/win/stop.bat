@echo off
echo "Stopping TAWATCH Application..."
cd ../.. || exit /b 1
docker compose down
if %errorlevel% neq 0 (
    echo [ERROR] Failed to stop TAWATCH application.
    pause
    exit /b 1
)
echo "TAWATCH application stopped successfully."
pause
exit /b 0