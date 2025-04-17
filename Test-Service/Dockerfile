FROM openjdk:17-slim

WORKDIR /app

COPY target/test-service-0.0.1-SNAPSHOT.jar /app/test-service.jar

EXPOSE 8080

CMD ["java", "-jar", "test-service.jar"]