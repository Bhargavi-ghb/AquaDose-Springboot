# =========================
# Step 1: Build the Spring Boot application
# Using Maven + Java 21
# =========================
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy Maven POM first (for dependency caching)
COPY pom.xml .

# Copy source code
COPY src ./src

# Build the application (skip tests)
RUN mvn clean package -DskipTests

# =========================
# Step 2: Run the built JAR
# Using Java 21 runtime
# =========================
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (Render uses $PORT)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
