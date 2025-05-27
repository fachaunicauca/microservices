# ---------- Etapa 1: Compilación ----------
# Hacerla con el build.bat si es Windows o con el build.sh si es Linux

# ---------- Etapa 2: Imagen final ----------
FROM openjdk:17-slim

WORKDIR /app

COPY target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar

EXPOSE 8080
# Comando para ejecutar el .jar
CMD ["java", "-jar", "api-gateway.jar"]
