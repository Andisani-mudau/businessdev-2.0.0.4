# Render Build Checklist - Critical Items

## Current Issue
Application fails to start because `flow-build-info.json` is missing, causing Vaadin to try dev mode initialization.

## What to Check in Render Build Logs

### 1. Build Verification Step
Look for this in the build logs:
```
[INFO] Verifying Vaadin production bundle after build-frontend...
[INFO] Checking: /app/target/classes/META-INF/VAADIN/config/flow-build-info.json
```

**Expected outcomes:**
- ✅ `[INFO] ✓ flow-build-info.json found at: ...` → Build should succeed
- ❌ `[ERROR] flow-build-info.json is missing!` → Build should fail with this error

**If verification fails:**
- The `build-frontend` goal didn't complete successfully
- Check for Node.js/npm errors above this message
- Check if build timed out

### 2. Frontend Build Steps
Look for these in order:
```
[INFO] --- vaadin-maven-plugin:24.5.0:prepare-frontend (prepare-frontend-prod)
[INFO] Using Node.js version v20.11.0
[INFO] Running 'npm install' ...
[INFO] --- vaadin-maven-plugin:24.5.0:build-frontend (build-frontend-prod)
[INFO] Building frontend bundle for production ...
```

**If these are missing:**
- The production profile isn't being used
- Check that build command includes `-Pproduction`

### 3. Node.js Availability
Look for:
```
[INFO] Using Node.js version v20.11.0
```
or
```
[ERROR] Node.js not found
```

**If Node.js is missing:**
- Render should provide it automatically
- May need to install Node.js in build command

### 4. Build Success/Failure
Look for:
```
[INFO] BUILD SUCCESS
```
or
```
[ERROR] BUILD FAILURE
```

**If build succeeds but app fails:**
- Check runtime logs (not build logs)
- The file might be generated but not included in JAR
- Check if Spring Boot plugin is excluding resources

## What We've Configured

1. ✅ **Build verification** in `pre-package` phase
2. ✅ **System property** `-Dvaadin.productionMode=true` in start command
3. ✅ **Production mode** in `application-prod.properties`
4. ✅ **Explicit phases** for frontend build goals

## Next Steps

1. **Deploy to Render** with these changes
2. **Check build logs** for the verification message
3. **If verification passes**: Check runtime logs for other issues
4. **If verification fails**: The error message will tell you why `build-frontend` failed

## Common Issues

### Issue: Build verification passes but app still fails
**Solution**: The file might be in the wrong location. Check if it's in `target/classes/META-INF/VAADIN/config/` after build.

### Issue: Build verification fails
**Solution**: Check why `build-frontend` didn't generate the file:
- Node.js not available
- npm install failed
- Build timed out
- Memory issues

### Issue: Build succeeds but file missing from JAR
**Solution**: Check if Spring Boot plugin is excluding resources. The file should be in `target/classes/` before packaging.

