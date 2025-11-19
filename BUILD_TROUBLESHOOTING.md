# Build Troubleshooting for Render

## VaadinServletContextInitializer Error

If you're seeing `VaadinServletContextInitializer` errors on Render, it usually means the frontend production bundle is missing.

### Solution 1: Verify Build Command

Ensure your Render build command is:
```bash
./mvnw clean package -Pproduction -DskipTests
```

### Solution 2: Check Build Logs

Look for these in the build logs:
- `[INFO] --- vaadin-maven-plugin:24.5.0:prepare-frontend`
- `[INFO] --- vaadin-maven-plugin:24.5.0:build-frontend`
- `[INFO] BUILD SUCCESS`

If `build-frontend` is missing or fails, that's the problem.

### Solution 3: Manual Build Verification

Before deploying, test the build locally:
```bash
./mvnw clean package -Pproduction -DskipTests
```

Then verify the JAR contains frontend resources:
```bash
jar -tf target/businessdev-1.0-SNAPSHOT.jar | grep -i vaadin
```

You should see entries like:
- `META-INF/VAADIN/config/flow-build-info.json`
- `META-INF/VAADIN/build/`

### Solution 4: Check Node.js Version

Vaadin requires Node.js. Render should provide it, but verify:
- Node.js 18+ is available
- npm/pnpm is available

### Solution 5: Increase Build Timeout

If the build times out:
- Frontend build can take 5-10 minutes
- Increase Render build timeout if needed
- Consider using a faster build instance

### Solution 6: Fallback - Disable Production Mode Temporarily

If nothing else works, temporarily disable production mode to test:

In `application-prod.properties`:
```properties
vaadin.production-mode=false
```

**Note:** This is NOT recommended for production, but can help diagnose the issue.

### Solution 7: Check Dependencies

Ensure all dependencies are resolved:
```bash
./mvnw dependency:tree
```

Look for any missing or conflicting Vaadin dependencies.

## Common Issues

1. **Build fails silently**: Check full build logs, not just errors
2. **Frontend not included**: Verify `build-frontend` goal ran
3. **Node modules missing**: Ensure `prepare-frontend` ran first
4. **Memory issues**: Frontend build needs memory - increase if needed

## Next Steps

1. Check Render build logs completely
2. Verify build command includes `-Pproduction`
3. Ensure build completes successfully
4. Check that JAR file size is reasonable (50-150MB)
5. Verify frontend resources are in JAR

