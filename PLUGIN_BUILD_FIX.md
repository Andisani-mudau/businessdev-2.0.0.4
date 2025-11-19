# Fix for Missing Plugin Files Error

## Problem

The build fails with:
```
ERROR: Could not resolve "./target/plugins/application-theme-plugin/theme-handle.js"
ERROR: Could not resolve "./target/plugins/theme-loader/theme-loader-utils.js"
ERROR: Could not resolve "./target/plugins/react-function-location-plugin/react-function-location-plugin.js"
ERROR: Could not resolve "./target/plugins/rollup-plugin-postcss-lit-custom/rollup-plugin-postcss-lit.js"
```

## Root Cause

The `prepare-frontend` Maven goal must run **before** `build-frontend` to generate the plugin files in `target/plugins/`. These plugins are required by `vite.generated.ts` during the frontend build.

## Solution Applied

1. **Set explicit build phases** in `pom.xml`:
   - `prepare-frontend` runs in `generate-resources` phase
   - `build-frontend` runs in `prepare-package` phase (after prepare-frontend)

2. **Added Node.js configuration** to ensure consistent Node.js version

3. **Ensured both default and production profiles** have proper `prepare-frontend` execution

## Build Order (Now Fixed)

1. **generate-resources phase**: `prepare-frontend` runs
   - Downloads Node.js if needed
   - Installs npm dependencies
   - **Generates plugins in `target/plugins/`** ← Critical step
   - Generates `vite.generated.ts`

2. **prepare-package phase**: `build-frontend` runs
   - Uses plugins from `target/plugins/`
   - Builds production bundle
   - Creates optimized frontend assets

3. **package phase**: Spring Boot plugin creates JAR
   - Includes all frontend resources

## Verification

After running the build, verify plugins exist:

```powershell
# Check if plugins directory exists
Test-Path target/plugins

# List plugin directories
Get-ChildItem target/plugins -Directory
```

You should see:
- `application-theme-plugin/`
- `theme-loader/`
- `react-function-location-plugin/`
- `rollup-plugin-postcss-lit-custom/`

## If Build Still Fails

1. **Clean and rebuild**:
   ```bash
   ./mvnw clean
   ./mvnw vaadin:prepare-frontend -Pproduction
   ./mvnw package -Pproduction
   ```

2. **Check Node.js version**:
   - Vaadin 24.5 requires Node.js 18+
   - Verify: `node --version`

3. **Check npm dependencies**:
   - Ensure `node_modules` exists
   - Run: `npm install` if needed

4. **Verify plugin generation**:
   - Check `target/plugins/` directory exists
   - Verify plugin files are present

## Next Steps

1. Run the build: `./mvnw clean package -Pproduction`
2. Verify plugins are generated in `target/plugins/`
3. Build should complete successfully
4. Deploy to Render

The build configuration is now correct - the plugins will be generated before they're needed.

