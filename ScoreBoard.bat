@ECHO OFF 
:: This batch file runs scoreboard program onn windows.
TITLE ScoreBoard
ECHO Please wait... Creating and running container.
docker build -t my-java-app .

cls
docker run -it --rm --name my-running-app my-java-app
PAUSE