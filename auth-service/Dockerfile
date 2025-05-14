# Dockerfile do auth-service
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /workspace
COPY . .

# Dê permissão de execução ao mvnw
RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests -pl auth-service -am

# Runtime stage
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=builder /workspace/auth-service/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]