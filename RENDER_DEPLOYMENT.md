# Render Deployment Guide

This guide helps you deploy the BusinessDev application to Render.

## Prerequisites

1. A Render account
2. MongoDB database (Render MongoDB or MongoDB Atlas)
3. All required API keys

## Deployment Steps

### Option 1: Using render.yaml (Recommended)

1. Push your code to GitHub
2. In Render dashboard, create a new "Web Service"
3. Connect your GitHub repository
4. Render will automatically detect `render.yaml` and use those settings
5. Add your environment variables in the Render dashboard

### Option 2: Manual Configuration

1. **Create a Web Service** in Render
2. **Build Command:**
   ```
   ./mvnw clean package -Pproduction
   ```
   
   **Note:** This will run tests. To skip tests (not recommended), add `-DskipTests`.
3. **Start Command:**
   ```
   java -jar target/businessdev-1.0-SNAPSHOT.jar --spring.profiles.active=prod
   ```
4. **Environment:** Java
5. **Java Version:** 17

## Required Environment Variables

Set these in the Render dashboard under "Environment":

```bash
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=8080
JAVA_OPTS=-Xmx512m -Xms256m -XX:+UseG1GC

# MongoDB
MONGODB_URI=mongodb+srv://username:password@cluster.mongodb.net/businessdev?retryWrites=true&w=majority

# API Keys
CURRENCY_API_KEY=your_currency_api_key
BREVO_API_KEY=your_brevo_api_key
BREVO_SENDER_EMAIL=your_email@example.com
BREVO_SENDER_NAME=Your Name

# Google Calendar (if used)
GOOGLE_CALENDAR_ID=your_calendar_id
GOOGLE_CALENDAR_CREDENTIALS_FILE=/path/to/credentials.json

# Application Settings
APP_TIMEZONE=Africa/Johannesburg
BUSINESS_HOURS_START=9
BUSINESS_HOURS_END=17
```

## Important Notes

1. **Build Time:** The production build can take 5-10 minutes. Be patient.

2. **Memory:** Default memory is 512MB. If you encounter out-of-memory errors, increase it in Render settings.

3. **MongoDB:** 
   - Use MongoDB Atlas for production (recommended)
   - Or use Render's MongoDB service
   - Make sure to whitelist Render's IP addresses

4. **Health Checks:** The application includes health checks at `/actuator/health`

5. **Logs:** Check Render logs if deployment fails. Common issues:
   - Missing environment variables
   - MongoDB connection issues
   - Frontend build not included (make sure to use `-Pproduction`)

## Troubleshooting

### Application fails to start

1. Check Render logs for specific errors
2. Verify all environment variables are set
3. Ensure MongoDB is accessible from Render
4. Check that the build completed successfully

### VaadinServletContextInitializer errors

This usually means:
- Frontend build is missing (rebuild with `-Pproduction`)
- Production bundle not included in JAR
- Check that `vaadin.production-mode=true` is set

### MongoDB connection errors

1. Verify MongoDB URI is correct
2. Check IP whitelist in MongoDB Atlas
3. Verify credentials are correct
4. Test connection string locally first

## Build Verification

After deployment, verify:
- Application starts without errors
- Health endpoint works: `https://your-app.onrender.com/actuator/health`
- Main page loads: `https://your-app.onrender.com`

## Support

If you encounter issues:
1. Check Render logs
2. Verify all environment variables
3. Test MongoDB connection
4. Review application logs in Render dashboard

