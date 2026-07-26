# Estágio 1: Compilação (Build usando o Java 25 + Maven Wrapper)
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

# Copia os arquivos do Maven Wrapper primeiro (para aproveitar o cache do Docker)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copia o código-fonte e gera o pacote
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Estágio 2: Execução (Imagem leve de Runtime)
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]