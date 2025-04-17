FROM openjdk:17-slim

WORKDIR /app

COPY target/inventory-service-0.0.1-SNAPSHOT.jar /app/inventory-service.jar

EXPOSE 8080

CMD ["java", "-jar", "inventory-service.jar"]