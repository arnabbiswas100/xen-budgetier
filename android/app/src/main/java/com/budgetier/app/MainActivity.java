package com.budgetier.app;

import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    private String currentTheme = "dark";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply system bar insets padding ONLY on TABLETS (sw600dp or large screen)
        // For mobile phones, keep layout exactly as v1.0.2 with zero extra top space
        if (isTabletDevice()) {
            applyTabletSystemBarInsets();
        }

        // Ensure status bar starts in dark mode (white icons on dark status bar)
        setStatusBarThemeNative("dark");

        // Expose a native JavaScript bridge to seamlessly switch status bar theme from Web
        if (bridge != null && bridge.getWebView() != null) {
            bridge.getWebView().addJavascriptInterface(new Object() {
                @JavascriptInterface
                public void setStatusBarTheme(final String theme) {
                    runOnUiThread(() -> setStatusBarThemeNative(theme));
                }
            }, "AndroidBridge");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setStatusBarThemeNative(currentTheme);
        if (isTabletDevice()) {
            View contentView = findViewById(android.R.id.content);
            if (contentView != null) {
                contentView.requestApplyInsets();
            }
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (isTabletDevice()) {
            applyTabletSystemBarInsets();
        }
    }

    private boolean isTabletDevice() {
        Configuration config = getResources().getConfiguration();
        boolean isSw600 = config.smallestScreenWidthDp >= 600;
        boolean isLarge = (config.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        return isSw600 || isLarge;
    }

    private void applyTabletSystemBarInsets() {
        Window window = getWindow();
        if (window == null) return;

        // On tablets: allow drawing behind status bar for seamless background coloring,
        // while padding the root content view by systemBars.top so top nav never overlaps notification panel.
        WindowCompat.setDecorFitsSystemWindows(window, false);

        View contentView = findViewById(android.R.id.content);
        if (contentView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(contentView, (v, windowInsets) -> {
                Insets systemBars = windowInsets.getInsets(
                    WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout()
                );
                Insets imeInsets = windowInsets.getInsets(WindowInsetsCompat.Type.ime());
                boolean imeVisible = windowInsets.isVisible(WindowInsetsCompat.Type.ime());

                int bottomPadding = imeVisible ? imeInsets.bottom : systemBars.bottom;

                v.setPadding(systemBars.left, systemBars.top, systemBars.right, bottomPadding);

                return windowInsets;
            });
            contentView.requestApplyInsets();
        }
    }

    private void setStatusBarThemeNative(String theme) {
        this.currentTheme = theme;
        Window window = getWindow();
        if (window == null) return;

        boolean isLight = "light".equalsIgnoreCase(theme);
        WindowInsetsControllerCompat controller =
            WindowCompat.getInsetsController(window, window.getDecorView());

        if (controller != null) {
            // isAppearanceLightStatusBars(true) => dark text/icons (for light theme)
            // isAppearanceLightStatusBars(false) => light/white text/icons (for dark theme)
            controller.setAppearanceLightStatusBars(isLight);
            controller.setAppearanceLightNavigationBars(isLight);
        }

        // Match status bar and nav bar colors with app background
        int statusBarColor = isLight ? 0xFFF5F0E6 : 0xFF110E0A;
        int navBarColor = isLight ? 0xFFF7F2E7 : 0xFF16120D;
        window.setStatusBarColor(statusBarColor);
        window.setNavigationBarColor(navBarColor);
        window.setBackgroundDrawable(new ColorDrawable(statusBarColor));

        if (isTabletDevice()) {
            View contentView = findViewById(android.R.id.content);
            if (contentView != null) {
                contentView.setBackgroundColor(statusBarColor);
            }
        }
    }
}
