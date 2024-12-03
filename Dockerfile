# Build stage
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml . 
COPY src ./src
VOLUME /root/.m2  # Cache Maven dependencies
RUN mvn clean package -Pproduction

# Run stage
FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=prod
WORKDIR /app
COPY --from=build /app/target/businessdev-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
