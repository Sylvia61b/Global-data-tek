# Use a base image with Java
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/cloud-gateway-0.0.1-SNAPSHOT.jar cloud-gateway.jar

# Expose the application port
EXPOSE 8989

# Run the application
ENTRYPOINT ["java", "-jar", "cloud-gateway.jar"]