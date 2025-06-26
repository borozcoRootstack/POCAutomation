package steps;

import com.microsoft.playwright.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.awt.*;
import java.nio.file.Paths;

public class Hooks {

    public static Playwright playwright;
    public static Browser browser;
    public static Page page;
    public static BrowserContext context;

    @Before
    public void setUp() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false) // Puedes poner true si lo quieres sin UI
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
