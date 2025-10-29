# Stage 1: build with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy only the files needed to download deps (cache layer)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Download dependencies (cache layer)
RUN mvn dependency:go-offline -B

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests -B

# Stage 2: run with minimal JRE
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy artifact from build stage (ensure name matches target jar)
COPY --from=build /app/target/billing-system-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
