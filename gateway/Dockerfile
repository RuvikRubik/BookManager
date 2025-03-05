# Use Eclipse Temurin as base image for Java 17
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the .jar file from your local machine into the container
COPY out/artifacts/gateway_jar/gateway.jar /app/gateway-app.jar

# Expose the appropriate port (the port the gateway will be listening to)
EXPOSE 8080

# Run the Spring Boot application (Spring Cloud Gateway)
ENTRYPOINT ["java", "-jar", "/app/gateway-app.jar"]