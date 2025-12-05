@echo off
echo "Building TAWATCH Docker Images..."
cd ../.. || exit /b 1
docker compose build
if %errorlevel% neq 0 (
    echo [ERROR] Docker image build failed.
    pause
    exit /b 1
)
echo "Docker images built successfully."
pause
exit /b 0