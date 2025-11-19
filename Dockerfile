# Build stage
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

# Install Node.js for Vaadin frontend build
RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs && \
    node --version && \
    npm --version

# Copy pom.xml first for better layer caching
COPY pom.xml .
# Download dependencies (this layer will be cached if pom.xml doesn't change)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Copy .npmrc if it exists
COPY .npmrc* ./

# Cache Maven dependencies
VOLUME /root/.m2

# Build the application with production profile
RUN mvn clean package -Pproduction

# Verify the build produced the required file
RUN echo "Verifying Vaadin production bundle..." && \
    if [ ! -f target/classes/META-INF/VAADIN/config/flow-build-info.json ]; then \
        echo "ERROR: flow-build-info.json is missing!" && \
        echo "Checking target/classes/META-INF/VAADIN/config/:" && \
        ls -la target/classes/META-INF/VAADIN/config/ 2>/dev/null || echo "Directory does not exist" && \
        echo "Checking target/classes/META-INF/VAADIN/:" && \
        ls -la target/classes/META-INF/VAADIN/ 2>/dev/null || echo "Directory does not exist" && \
        exit 1; \
    else \
        echo "✓ flow-build-info.json found at: target/classes/META-INF/VAADIN/config/flow-build-info.json"; \
    fi

# Run stage
FROM eclipse-temurin:17-jre-alpine

# Create non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Set working directory
WORKDIR /app

# Create logs directory
RUN mkdir -p /app/logs

# Copy the built JAR from build stage
COPY --from=build /app/target/businessdev-1.0-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Set JVM options for production
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -Djava.security.egd=file:/dev/./urandom"

# Set Spring profile
ENV SPRING_PROFILES_ACTIVE=prod

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Run the application with production mode system property
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dvaadin.productionMode=true -jar /app/app.jar"]
