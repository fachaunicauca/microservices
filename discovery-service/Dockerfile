# ---------- Etapa 1: Compilación ----------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copia toda la estructura del proyecto
COPY . .


RUN mvn clean package -DskipTests

# ---------- Etapa 2: Imagen final ----------
FROM openjdk:17-slim

WORKDIR /app

# Copia el .jar generado en la etapa anterior
COPY --from=builder /app/discovery-service/target/discovery-service-0.0.1-SNAPSHOT.jar /app/discovery-service.jar

EXPOSE 8761
# Comando para ejecutar el .jar
CMD ["java", "-jar", "discovery-service.jar"]