# Use official OpenJDK 17 as the base image
# This provides a Linux environment with Java 17 pre-installed
FROM openjdk:17-jdk

# Set the working directory in the container
# All subsequent commands will be executed from this directory
# If the directory doesn't exist, Docker will create it automatically
WORKDIR /app

# Copy the JAR file from your build output (target folder)
# Source: target/citronix-0.0.1-SNAPSHOT.jar (from your local machine)
# Destination: /app/citronix.jar (in the container)
# The file is renamed to citronix.jar for simplicity
COPY target/citronix-0.0.1-SNAPSHOT.jar /app/citronix.jar

# Document that the container will listen on port 8080
# This is metadata only and doesn't actually publish the port
# To publish the port, use -p 8080:8080 when running the container
EXPOSE 8080

# Command to run when the container starts
# Uses JSON array format ["command", "param1", "param2"]
# java: The command to run
# -jar: Parameter indicating we're running a JAR file
# /app/citronix.jar: The path to our application JAR
ENTRYPOINT ["java", "-jar", "/app/citronix.jar"]