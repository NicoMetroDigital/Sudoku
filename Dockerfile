FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/Sudoku-0.0.1-SNAPSHOT.jar /app/sudoku-app.jar

ENTRYPOINT ["java", "-jar", "/app/sudoku-app.jar"]
