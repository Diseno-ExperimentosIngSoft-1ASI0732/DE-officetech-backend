FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar todos los archivos del proyecto
COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar el JAR desde la etapa anterior
COPY --from=builder /app/target/officetech.API-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
