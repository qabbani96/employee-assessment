# Use official JDK 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory in container
WORKDIR /app

# Copy compiled JAR into the container
COPY target/*.jar app.jar

# Create data directory inside container
RUN mkdir -p /app/data

# Copy employees.json from resources (if needed manually during build)
# This assumes you include it outside the JAR — optional if you do this step yourself
COPY src/main/resources/employees.json /app/data/employees.json

# Expose port used by Spring Boot
EXPOSE 8080

# Run Spring Boot app and pass the file path as a property
ENTRYPOINT ["java", "-jar", "app.jar", "--file.path=/app/data/employees.json"]