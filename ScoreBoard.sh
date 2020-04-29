#!/bin/bash
# This batch file runs scoreboard program onn windows.
echo 'Please wait... Creating and running container.'
docker build -t my-java-app .
clear
docker run -it --rm --name my-running-app my-java-app