@echo off
setlocal enabledelayedexpansion

echo ============================
echo Setting up environment...
echo ============================

set "CLASSPATH=bin"
for %%f in (lib\*.jar) do (
    set "CLASSPATH=!CLASSPATH!;%%f"
)

echo ============================
echo Compiling Java files...
echo ============================
javac -cp "!CLASSPATH!" -d bin src/pages/*.java src/tests/*.java

if %ERRORLEVEL% NEQ 0 (
    echo ╳ Compilation failed.
    pause
    exit /b %ERRORLEVEL%
)

echo ============================
echo Running tests...
echo ============================
java -cp "!CLASSPATH!" tests.LoginTest

pause
