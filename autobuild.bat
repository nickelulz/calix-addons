@echo off
REM Run Maven package
call mvn package

REM Check if the build was successful
if %ERRORLEVEL% neq 0 (
    echo Maven build failed. Exiting.
    exit /b %ERRORLEVEL%
)

REM Define source and destination paths
set SOURCE=target\calix-addons-1.0-SNAPSHOT.jar
set DESTINATION=..\dev-server\plugins\calix-addons-1.0-SNAPSHOT.jar

REM Move the generated jar file to the destination, overwriting if it exists
if exist "%SOURCE%" (
    move /Y "%SOURCE%" "%DESTINATION%"
    echo File moved successfully to %DESTINATION%.
) else (
    echo Source file %SOURCE% does not exist. Exiting.
    exit /b 1
)