# Build stage
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
VOLUME /tmp
COPY --from=build /app/target/*.war app.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.war"]
