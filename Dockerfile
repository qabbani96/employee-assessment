
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
RUN mkdir -p /app/data
COPY src/main/resources/employees.json /app/data/employees.json
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "--file.path=/app/data/employees.json"]