package steps.login;

import io.cucumber.java.en.*;
import pages.LoginPage;
import steps.Hooks;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    LoginPage loginPage;
    List<Map<String, String>> loginData;

    public LoginSteps() {
        try {
            ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/login_data.xlsx");
            loginData = excelReader.getData("Sheet1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Given("the user navigates to the login page")
    public void navigateToLoginPage() {
        Hooks.page.navigate("https://rc.ceifx.com/index.php");
        loginPage = new LoginPage(Hooks.page);
    }

    @When("the user logs in using Excel row {int}")
    public void userLogsInWithExcelRow(int rowIndex) {
        Map<String, String> row = loginData.get(rowIndex);
        loginPage.enterCompany(row.get("company"));
        loginPage.enterUsername(row.get("username"));
        loginPage.enterPassword(row.get("password"));
        loginPage.clickLogin();
    }

    @Then("the user should be redirected to the home page as {string}")
    public void verifyHomePage(String expectedUsername) {
        String logoutText = loginPage.getLogoutText();
        assertTrue("Expected user '" + expectedUsername + "' not found in logout text: " + logoutText,
                logoutText.contains(expectedUsername));
    }
}
