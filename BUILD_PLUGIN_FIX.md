# Fix for Missing Plugin Files During Build

## Problem

Build fails with:
```
ERROR: Could not resolve "./target/plugins/application-theme-plugin/theme-handle.js"
ERROR: Could not resolve "./target/plugins/theme-loader/theme-loader-utils.js"
ERROR: Could not resolve "./target/plugins/react-function-location-plugin/react-function-location-plugin.js"
ERROR: Could not resolve "./target/plugins/rollup-plugin-postcss-lit-custom/rollup-plugin-postcss-lit.js"
```

## Root Cause

The `prepare-frontend` Maven goal must generate plugin files in `target/plugins/` before `build-frontend` runs. These plugins are required by `vite.generated.ts`.

## Solution

The build configuration has been updated to ensure:
1. `prepare-frontend` runs in `generate-resources` phase (before build-frontend)
2. Unique execution IDs to avoid conflicts
3. Proper Node.js configuration

## Manual Fix (If Build Still Fails)

If the build still fails, try running `prepare-frontend` manually first:

```powershell
# Step 1: Clean
.\mvnw.cmd clean

# Step 2: Run prepare-frontend to generate plugins
.\mvnw.cmd vaadin:prepare-frontend -Pproduction

# Step 3: Verify plugins exist
Test-Path target/plugins

# Step 4: Build
.\mvnw.cmd package -Pproduction
```

## Verification

After `prepare-frontend` runs, check:
```powershell
Get-ChildItem target/plugins -Directory
```

You should see:
- `application-theme-plugin/`
- `theme-loader/`
- `react-function-location-plugin/`
- `rollup-plugin-postcss-lit-custom/`

## Alternative: Use Development Mode Temporarily

If the production build continues to fail, you can temporarily use development mode:

In `application-prod.properties`:
```properties
vaadin.production-mode=false
```

This will allow the application to start, but frontend won't be optimized.

