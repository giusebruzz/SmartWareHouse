FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /build
COPY pom.xml ./
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
RUN useradd -m appuser
USER appuser
COPY --from=builder /build/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "sleep 10 && java -jar app.jar"]


