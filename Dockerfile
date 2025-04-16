# Stage 1: Build mit Gradle Wrapper
FROM gradle:7.4.2-jdk17 AS builder

WORKDIR /app

COPY gradlew gradlew.bat build.gradle.kts settings.gradle.kts /app/
COPY gradle /app/gradle
COPY src /app/src

RUN chmod +x ./gradlew && ./gradlew clean build --no-daemon

# Stage 2: Runtime - nur das fertige .jar verwenden
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
