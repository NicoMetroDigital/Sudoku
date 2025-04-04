# Dockerfile für das Backend

# Verwende das OpenJDK-Image als Basis
FROM openjdk:17-jdk-slim

# Setze das Arbeitsverzeichnis
WORKDIR /app

# Kopiere alle benötigten Dateien (auch settings.gradle) in das Arbeitsverzeichnis im Container
COPY gradlew build.gradle settings.gradle /app/

# Kopiere den gesamten Backend-Code
COPY src /app/src

# Setze den Befehl zum Starten des Backends
CMD ["./gradlew", "bootRun"]
