FROM openjdk:17-jdk
WORKDIR /app
COPY target/citronix-0.0.1-SNAPSHOT.jar /app/citronix.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/citronix.jar"]