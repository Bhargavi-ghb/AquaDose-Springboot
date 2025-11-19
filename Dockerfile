# =========================
# Step 1: Build the Spring Boot application
# =========================
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build Spring Boot JAR (skip tests)
RUN mvn clean package -DskipTests


# =========================
# Step 2: Run the built application
# =========================
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Render automatically injects $PORT
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
