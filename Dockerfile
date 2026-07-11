# Estágio 1: Compilação (Build)
FROM maven:3.9.10-eclipse-temurin-25-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio 2: Execução (Run)
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
# Copia o JAR gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]