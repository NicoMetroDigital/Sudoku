FROM openjdk:17-jdk-slim
# Setze das Arbeitsverzeichnis
WORKDIR /app

# Kopiere die gebaute JAR-Datei ins Image
COPY build/libs/Sudoku-0.0.1-SNAPSHOT.jar /app/sudoku-app.jar

# Exponiere den Port (nicht zwingend nötig, aber hilfreich)
EXPOSE 8080

# Setze den Container-Startbefehl
ENTRYPOINT ["java", "-jar", "/app/sudoku-app.jar"]
