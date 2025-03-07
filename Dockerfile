FROM openjdk:17-jdk-slim

# Setze das Arbeitsverzeichnis
WORKDIR /app

# Kopiere den gesamten Backend-Code ins Image
COPY . .

# Mache das Gradle-Skript ausführbar
RUN chmod +x gradlew

# Exponiere den Port (optional, aber hilfreich)
EXPOSE 8080

# Starte das Backend mit Gradle
ENTRYPOINT ["./gradlew", "bootRun"]
