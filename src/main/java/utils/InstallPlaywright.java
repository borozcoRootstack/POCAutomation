package utils;

import com.microsoft.playwright.*;

public class InstallPlaywright {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            System.out.println("✅ Playwright initialized successfully.");
            System.out.println("✅ Browsers should be downloaded and ready.");
        } catch (Exception e) {
            System.err.println("❌ Failed to initialize Playwright:");
            e.printStackTrace();
        }
    }
}
