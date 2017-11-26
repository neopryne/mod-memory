@ECHO OFF
SETLOCAL
SET L4J_LOG_NAME=launch4j.log
SET SMM_LOG_NAME=modman-log.txt
SET L4J_LOG_PATH=%~dp0launch4j.log
SET SMM_LOG_PATH=%~dp0modman-log.txt

IF EXIST "%L4J_LOG_PATH%" DEL /Q "%L4J_LOG_PATH%"
IF EXIST "%SMM_LOG_PATH%" DEL /Q "%SMM_LOG_PATH%"

ECHO.
ECHO.
ECHO This script will try to run Slipstream Mod Manager (as Admin).
ECHO.
ECHO Then logs will be presented in multiple notepad windows.
ECHO.
ECHO.
PAUSE
ECHO.

ECHO Running Slipstream...
ECHO.
"%~dp0modman_admin.exe" --l4j-debug

ECHO Spawning notepad...
ECHO.
IF EXIST "%L4J_LOG_PATH%" (
  ECHO For Java issues, see "%L4J_LOG_NAME%"
  ECHO.
  START /B notepad.exe "%L4J_LOG_PATH%"
) ELSE (
  ECHO Missing log: "%L4J_LOG_NAME%"
)

IF EXIST "%SMM_LOG_PATH%" (
  ECHO For Slipstream issues, see "%SMM_LOG_NAME%"
  ECHO.
  START /B notepad.exe "%SMM_LOG_PATH%"
) ELSE (
  ECHO Missing log: "%SMM_LOG_NAME%"
)

ECHO.
ECHO.
ECHO This script will now exit.
ECHO.
PAUSE
ENDLOCAL & EXIT /B
