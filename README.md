# Budgetier

A beautiful, glassmorphic personal finance and budget tracking application designed for students and everyday budgeting. Built with 100% client-side technologies, it operates completely offline with zero backend, zero trackers, and zero cloud lock-in.

---

## Key Features

- **100% Offline & Private**: All data is saved on the user's device using local storage (`budgetier_*`). No data ever leaves the phone or browser.
- **Packaged for Android (Capacitor Ready)**: Bundles native web assets (`index.html`, `style.css`, `script.js`, `chart.umd.min.js`) into a signed Android APK.
- **Smart Budget Carryover**: Seamlessly carries forward the previous month's budget when a new month begins.
- **Daily Budget Recommendations**: Dynamically calculates how much you can spend per day to stay on track throughout the month.
- **Undo for Deleted Expenses**: Accidentally delete a record? Restore it with a single tap via the Undo toast notification.
- **Recurring Expenses Management**: Set recurring bills and stop rules anytime directly from Settings.
- **Category & Sub-Category Limits**: Custom category budgets with real-time progress bars and overspending alerts.
- **Warm Glassmorphic UI**: Earthy palette (olive greens, caramel browns, sage, cream), fluid animations, dark/light theme ripple transitions, and mobile bottom sheet modals.
- **Full Data Portability**: Export/import complete JSON backups with validation, plus formula-safe CSV exports for spreadsheet analysis.

---

## File Structure

```
budgetier/
├── index.html         # Application shell, navigation, modals, and templates
├── style.css          # Glassmorphism design tokens, CSS variables, mobile layouts
├── script.js          # App state, calculations, insights, Chart.js integrations
├── chart.umd.min.js   # Bundled Chart.js v4.4.0 (local offline chart engine)
└── README.md          # Documentation and build instructions
```

---

## Android APK Build (Capacitor)

To package Budgetier as a standalone offline Android app:

### 1. Initialize Capacitor
```bash
npm init -y
npm install @capacitor/core @capacitor/cli @capacitor/android
npx cap init "Budgetier" "com.xen.budgetier" --web-dir .
npx cap add android
```

### 2. Configure `capacitor.config.json`
```json
{
  "appId": "com.xen.budgetier",
  "appName": "Budgetier",
  "webDir": ".",
  "bundledWebRuntime": false
}
```

### 3. Sync & Open in Android Studio
```bash
npx cap copy
npx cap sync
npx cap open android
```

### 4. Build Signed Release APK
In Android Studio:
1. Go to **Build** → **Generate Signed Bundle / APK**.
2. Select **APK**.
3. Create or specify your Release Keystore (`.jks`).
4. Select build variant `release` and click **Create**.
5. Your signed APK will be generated under `android/app/release/app-release.apk`.

---

## Running in the Browser

Open `index.html` directly in any modern browser:
```bash
open index.html
# Or serve with any lightweight static server:
npx serve .
```
