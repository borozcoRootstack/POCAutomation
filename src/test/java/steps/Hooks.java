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

        // Usar Google Chrome real
        String chromePath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setExecutablePath(Paths.get(chromePath))
                .setHeadless(false)
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
