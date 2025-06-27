package steps;

import com.microsoft.playwright.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    public static Playwright playwright;
    public static Browser browser;
    public static Page page;
    public static BrowserContext context;

    @Before
    public void setUp() {
        playwright = Playwright.create();

        boolean isCI = System.getenv("CI") != null;

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(isCI) // 🔹 true si está en CI, false localmente
        );

        context = browser.newContext();
        page = context.newPage();
    }

    @After
    public void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }
}
