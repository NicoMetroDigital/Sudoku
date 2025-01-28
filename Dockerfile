FROM openjdk:17-jdk-slim

WORKDIR /app

# Kopiere die gebaute JAR-Datei ins Image
COPY build/libs/Sudoku-0.0.1-SNAPSHOT.jar /app/sudoku-app.jar

# Starte die Anwendung
ENTRYPOINT ["java", "-jar", "/app/sudoku-app.jar"]
