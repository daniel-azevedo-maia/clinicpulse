########################
# Stage 1 – Build JAR  #
########################
FROM maven:3.9.4-eclipse-temurin-21-alpine AS builder

# cache do repositório Maven → builds muito mais rápidos
RUN --mount=type=cache,target=/root/.m2 echo "Usando cache Maven"

WORKDIR /workspace
COPY . .

# compila **somente** o módulo auth-service
RUN mvn -pl auth-service -am clean package -DskipTests

########################
# Stage 2 – Runtime    #
########################
FROM eclipse-temurin:21-jdk-alpine

# usuário não-root
RUN adduser -D -u 1000 appuser
USER appuser

WORKDIR /app
COPY --from=builder /workspace/auth-service/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
