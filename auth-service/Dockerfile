# ---------- Etapa 1: Compilación ----------
# Hacerla con el build.bat si es Windows o con el build.sh si es Linux

# ---------- Etapa 2: Imagen final ----------
FROM openjdk:17-slim

WORKDIR /app

COPY target/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar

# Comando para ejecutar el .jar
CMD ["java", "-jar", "auth-service.jar"]
