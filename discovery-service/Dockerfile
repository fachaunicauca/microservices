# ---------- Etapa 1: Compilación ----------
# Hacerla con el build.bat si es Windows o con el build.sh si es Linux

# ---------- Etapa 2: Imagen final ----------
FROM openjdk:17-slim

WORKDIR /app

COPY target/discovery-service-0.0.1-SNAPSHOT.jar discovery-service.jar

EXPOSE 8761
# Comando para ejecutar el .jar
CMD ["java", "-jar", "discovery-service.jar"]