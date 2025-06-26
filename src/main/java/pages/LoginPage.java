package pages;

import com.microsoft.playwright.Page;
import static locators.LoginLocators.*;
public class LoginPage {

    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void enterCompany(String company) {
        page.locator(COMPANY_INPUT).fill(company);
    }

    public void enterUsername(String username) {
        page.locator(USERNAME_INPUT).fill(username);
    }

    public void enterPassword(String password) {
        page.locator(PASSWORD_INPUT).fill(password);
    }

    public void clickLogin() {
        page.locator(LOGIN_BTN).click();
    }

    public String getLogoutText() {
        return page.locator(LOGOUT_BTN).textContent().trim();
    }
}
