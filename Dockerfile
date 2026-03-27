from maven:3.9-eclipse-temurin-21 AS builder
workdir /opt/app
copy pom.xml ./
copy src ./src
RUN mvn package -DskipTests

from eclipse-temurin:21-jre
workdir /opt/app/SmartWareHouse
run useradd -m app
user app
copy --from=builder /opt/app/target/*.jar /opt/app/app.jar
expose 8080
CMD ["java", "-jar", "/opt/app/app.jar"]


