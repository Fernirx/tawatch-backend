@echo off
setlocal enableddelayedexpansion

echo =============================================
echo           TAWATCH SETUP (Windows)
echo =============================================

:: 1. Check docker
where docker >nul 2>nul
if %errorlevel% neq 0 (
    echo [ERROR] Docker is not installed or not found in PATH. Please install Docker and try again.
    pause
    exit /b 1
)

:: 2. Check docker compose v2
where docker compose version >nul 2>nul
if %errorlevel% neq 0 (
    echo [ERROR] Docker Compose V2 is not installed or not found in PATH. Please install Docker Compose V2 and try again.
    pause
    exit /b 1
)

:: 3. Check .env file
cd ../.. || exit /b 1
if not exist ".env" (
    echo [ERROR] .env file not found. Creating from .env.example...

    if not exist ".env.example" (
        echo [ERROR] .env.example file not found. Cannot create .env file.
        echo [ERROR] Please check repository for the .env.example template.
        pause
        exit /b 1
    ) else (
        copy ".env.example" ".env" >nul 2>nul
        echo [INFO] .env file created from .env.example template.
    )
)

:: 4. Check application-docker.yml file
set APP_PROFILE_FILE=tawatch-starter\src\main\resources\application-docker.yml
set APP_PROFILE_EXAMPLE_FILE=tawatch-starter\src\main\resources\application.example.yml

if not exist %APP_PROFILE_FILE% (
    echo [ERROR] application-docker.yml file not found. Creating from application.example.yml...

    if not exist %APP_PROFILE_EXAMPLE_FILE% (
        echo [ERROR] application.example.yml file not found. Cannot create application-docker.yml file.
        echo [ERROR] Please check repository for the application.example.yml template.
        pause
        exit /b 1
    ) else (
        copy %APP_PROFILE_EXAMPLE_FILE% %APP_PROFILE_FILE% >nul 2>nul
        echo [INFO] application-docker.yml file created from application.example.yml template.
    )
)

echo .
echo ============================================
echo           SETUP COMPLETED SUCCESSFULLY
echo ============================================
pause
exit /b 0