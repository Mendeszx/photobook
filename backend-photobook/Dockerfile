FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /backend

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests -X

COPY target/backend-photobook-SNAPSHOT.jar .

CMD ["java", "-jar", "-Dspring.profiles.active=prd", "backend-photobook-SNAPSHOT.jar"]
