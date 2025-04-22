# ---------- Etapa 1: Compilación ----------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copia todo el proyecto al contenedor
COPY . .

# Compila el proyecto y genera el .jar
RUN mvn clean package -DskipTests

# ---------- Etapa 2: Imagen final ----------
FROM openjdk:17-slim

WORKDIR /app

# Copia el .jar generado en la etapa anterior
COPY --from=builder /app/test-service/target/test-service-0.0.1-SNAPSHOT.jar /app/test-service.jar

# Comando para ejecutar el .jar
CMD ["java", "-jar", "test-service.jar"]
