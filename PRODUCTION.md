# Production Deployment Guide

This document provides instructions for deploying the BusinessDev application to production.

## Prerequisites

- Java 17 or higher
- Maven 3.8+
- MongoDB 7.0+ (or MongoDB Atlas)
- Docker and Docker Compose (optional, for containerized deployment)

## Environment Variables

Create a `.env` file in the project root with the following variables:

```bash
# Application Configuration
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=8080

# MongoDB Configuration
# For local: mongodb://localhost:27017/businessdev
# For Atlas: mongodb+srv://username:password@cluster.mongodb.net/businessdev?retryWrites=true&w=majority
MONGODB_URI=mongodb://localhost:27017/businessdev

# External API Keys
CURRENCY_API_KEY=your_currency_api_key_here
BREVO_API_KEY=your_brevo_api_key_here
BREVO_SENDER_EMAIL=your_sender_email@example.com
BREVO_SENDER_NAME=Your Sender Name

# Google Calendar Configuration
GOOGLE_CALENDAR_ID=your_google_calendar_id
GOOGLE_CALENDAR_CREDENTIALS_FILE=path/to/your/credentials.json

# Application Settings
APP_TIMEZONE=Africa/Johannesburg
BUSINESS_HOURS_START=9
BUSINESS_HOURS_END=17

# Logging (Optional)
LOG_FILE=logs/businessdev.log
```

## Building for Production

### Option 1: Maven Build

```bash
mvn clean package -Pproduction
```

This will:
- Build the frontend with production optimizations
- Create an optimized JAR file in `target/businessdev-1.0-SNAPSHOT.jar`
- Enable production mode for Vaadin

### Option 2: Docker Build

```bash
docker build -t businessdev:latest .
```

## Running in Production

### Option 1: Standalone JAR

```bash
java -jar -Xmx512m -Xms256m target/businessdev-1.0-SNAPSHOT.jar
```

### Option 2: Docker Compose

```bash
docker-compose up -d
```

This will:
- Start MongoDB container
- Start the application container
- Set up health checks
- Configure automatic restarts

### Option 3: Docker Run

```bash
docker run -d \
  --name businessdev-app \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e MONGODB_URI=mongodb://your-mongodb-host:27017/businessdev \
  -e CURRENCY_API_KEY=your_key \
  -e BREVO_API_KEY=your_key \
  -v $(pwd)/logs:/app/logs \
  businessdev:latest
```

## Health Checks

The application includes Spring Boot Actuator for health monitoring:

- Health endpoint: `http://localhost:8080/actuator/health`
- Info endpoint: `http://localhost:8080/actuator/info`

## Logging

Production logs are written to:
- Console (for containerized deployments)
- File: `logs/businessdev.log` (with rotation)

Log files are automatically rotated:
- Maximum file size: 10MB
- Retention: 30 days
- Total size cap: 1GB

## Security Considerations

1. **Environment Variables**: Never commit `.env` files or credentials to version control
2. **MongoDB**: Use authentication and SSL/TLS in production
3. **API Keys**: Store all API keys in environment variables
4. **HTTPS**: Configure reverse proxy (nginx/Apache) with SSL certificates
5. **Firewall**: Only expose necessary ports (8080 or 443/80)

## Performance Tuning

### JVM Options

Recommended JVM options for production:

```bash
-Xmx512m -Xms256m -XX:+UseG1GC -XX:MaxGCPauseMillis=200
```

### MongoDB Connection Pooling

MongoDB connection pooling is handled automatically by Spring Data MongoDB. For high-traffic scenarios, consider:

- Using MongoDB Atlas with connection pooling
- Configuring replica sets for read scaling
- Implementing caching layer (Redis) if needed

## Monitoring

### Application Metrics

Monitor the following:
- Application health: `/actuator/health`
- Memory usage: JVM metrics
- Response times: Application logs
- Database connections: MongoDB monitoring

### Recommended Tools

- **Application Monitoring**: New Relic, Datadog, or Prometheus
- **Log Aggregation**: ELK Stack, Splunk, or CloudWatch
- **Uptime Monitoring**: Pingdom, UptimeRobot, or StatusCake

## Troubleshooting

### Application Won't Start

1. Check environment variables are set correctly
2. Verify MongoDB is accessible
3. Check logs: `logs/businessdev.log`
4. Verify port 8080 is not in use

### Database Connection Issues

1. Verify MongoDB URI format
2. Check network connectivity
3. Verify authentication credentials
4. Check MongoDB logs

### Performance Issues

1. Monitor JVM memory usage
2. Check database query performance
3. Review application logs for errors
4. Consider scaling horizontally

## Backup and Recovery

### Database Backups

```bash
# MongoDB backup
mongodump --uri="mongodb://localhost:27017/businessdev" --out=/backup/$(date +%Y%m%d)
```

### Application Backups

- Backup configuration files
- Backup uploaded files (if any)
- Document environment variables securely

## Updates and Maintenance

1. Pull latest code from repository
2. Update environment variables if needed
3. Rebuild application: `mvn clean package -Pproduction`
4. Stop current application
5. Deploy new JAR file
6. Start application
7. Verify health endpoint

## Support

For issues or questions, contact the development team.

