# Render Build Verification Guide

## Current Issue

The application fails to start on Render with `VaadinServletContextInitializer` error, indicating that `flow-build-info.json` is missing from the JAR.

## What We've Added

1. **Build Verification Step**: Added `maven-antrun-plugin` to verify that `flow-build-info.json` exists after the `build-frontend` goal runs
2. **Explicit Production Mode**: Set `productionMode=true` in both `prepare-frontend` and `build-frontend` configurations
3. **Fail on Error**: Set `failOnError=true` to ensure build fails if frontend build has issues

## How to Diagnose on Render

### Step 1: Check Build Logs

Look for these in the Render build logs:

1. **Node.js Availability**:
   ```
   [INFO] Node.js version: v20.11.0
   [INFO] npm version: ...
   ```

2. **Frontend Build Steps**:
   ```
   [INFO] --- vaadin-maven-plugin:24.5.0:prepare-frontend
   [INFO] --- vaadin-maven-plugin:24.5.0:build-frontend
   ```

3. **Build Verification**:
   ```
   [INFO] Verifying Vaadin production bundle...
   [INFO] ✓ flow-build-info.json found
   ```

4. **Build Success**:
   ```
   [INFO] BUILD SUCCESS
   ```

### Step 2: Check for Errors

Look for any of these errors:

- `[ERROR] Failed to execute goal ... build-frontend`
- `[ERROR] flow-build-info.json is missing!`
- `[ERROR] Node.js not found`
- `[ERROR] npm install failed`
- Build timeout errors

### Step 3: Verify Build Command

Ensure Render is using:
```bash
./mvnw clean package -Pproduction
```

### Step 4: Check Node.js

Render should provide Node.js automatically, but verify:
- Node.js 18+ is available
- npm is available
- Check Node.js version in build logs

## Common Issues and Solutions

### Issue 1: Build Times Out

**Symptom**: Build fails with timeout error before completion

**Solution**:
- Frontend build can take 5-10 minutes
- Increase Render build timeout in dashboard
- Consider using a faster build instance

### Issue 2: Node.js Not Available

**Symptom**: `[ERROR] Node.js not found` or npm errors

**Solution**:
- Render should provide Node.js automatically
- If not, add Node.js installation step to build command
- Or use a buildpack that includes Node.js

### Issue 3: build-frontend Goal Fails Silently

**Symptom**: Build completes but `flow-build-info.json` is missing

**Solution**:
- The verification step will now catch this and fail the build
- Check build logs for frontend build errors
- Look for npm/node errors

### Issue 4: File Generated But Not Included in JAR

**Symptom**: Verification passes but file missing from JAR

**Solution**:
- Check if file exists in `target/classes/META-INF/VAADIN/config/`
- Verify Spring Boot plugin includes resources
- Check for resource filtering issues

## Expected Build Output

A successful build should show:

```
[INFO] --- vaadin-maven-plugin:24.5.0:prepare-frontend (prepare-frontend-prod) @ businessdev ---
[INFO] Using Node.js version v20.11.0
[INFO] Running 'npm install' ...
[INFO] --- vaadin-maven-plugin:24.5.0:build-frontend (build-frontend-prod) @ businessdev ---
[INFO] Building frontend bundle for production ...
[INFO] Verifying Vaadin production bundle...
[INFO] ✓ flow-build-info.json found
[INFO] --- spring-boot-maven-plugin:3.3.4:repackage (default) @ businessdev ---
[INFO] BUILD SUCCESS
```

## Manual Verification

If you can access the build environment, verify manually:

```bash
# After build completes, check if file exists
ls -la target/classes/META-INF/VAADIN/config/flow-build-info.json

# Check JAR contents
jar -tf target/businessdev-1.0-SNAPSHOT.jar | grep "META-INF/VAADIN"
```

You should see:
- `META-INF/VAADIN/config/flow-build-info.json`
- `META-INF/VAADIN/build/` directory with files

## Next Steps

1. **Deploy to Render** with the updated configuration
2. **Check Build Logs** for the verification message
3. **If verification fails**: The build will fail with a clear error message
4. **If verification passes but app still fails**: Check runtime logs for other issues

## Additional Debugging

If the issue persists:

1. **Enable verbose logging** in Render:
   ```bash
   ./mvnw clean package -Pproduction -X
   ```

2. **Check Render environment variables**:
   - Ensure `SPRING_PROFILES_ACTIVE=prod`
   - Check for any conflicting settings

3. **Test locally first**:
   ```bash
   ./mvnw clean package -Pproduction
   java -jar target/businessdev-1.0-SNAPSHOT.jar --spring.profiles.active=prod
   ```

If it works locally but fails on Render, the issue is environment-specific (Node.js, memory, timeout, etc.).

