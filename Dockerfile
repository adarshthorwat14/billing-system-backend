# Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the code
COPY src ./src

# Build the Spring Boot project
RUN ./mvnw clean package -DskipTests

# Run the built jar
CMD ["java", "-jar", "target/billing-system-0.0.1-SNAPSHOT.jar"]
