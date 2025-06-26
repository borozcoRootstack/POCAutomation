package steps.EURToUSDTransaction;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.*;
import pages.EURToUSDTransactionPage;
import steps.Hooks;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class EURToUSDTransactionSteps {
    private EURToUSDTransactionPage transactionPage;
    private List<Map<String, String>> data;
    private Map<String, String> row;

    public EURToUSDTransactionSteps() {
        try {
            ExcelReader reader = new ExcelReader("src/test/resources/testdata/transaction_data.xlsx");
            data = reader.getData("Sheet1");
            Page playwrightPage = Hooks.page;
            this.transactionPage = new EURToUSDTransactionPage(playwrightPage);
        } catch (IOException e) {
            throw new RuntimeException("Error loading Excel data: " + e.getMessage());
        }
    }

    @Given("the user selects company location and starts transaction using Excel row {int}")
    public void userStartsTransaction(int index) {
        row = data.get(index);
        transactionPage.companyCode(row.get("companyCode"));
        transactionPage.locationCode(row.get("locationCode"));
        transactionPage.clickLoginLocation();
        transactionPage.clickSellToClient();

        transactionPage.selectCurrency(row.get("currency"));
        transactionPage.enterAmount(row.get("amount"));



    }

    @And("the system should round and adjust the amount as per Excel")
    public void validatePopup() {

        transactionPage.validatePopupAppeared();
        transactionPage.insertSpecialRequests(row.get("specialRequests"));
    }

    @And("validate rate and fees based on Excel data")
    public void validateRateAndFees() {

        transactionPage.insertRate(row.get("rate"));
        transactionPage.insertUSDValue(row.get("usdValue"));
        transactionPage.insertExchangeFee(row.get("exchangeFee"));
        transactionPage.insertSmallOrderFee(row.get("smallOrderFee"));
        transactionPage.insertSpecialRequests(row.get("specialRequests"));

    }

    @And("special fields should be visible in the receipt from Excel")
    public void enterClientDetails() {
        transactionPage.clickProceedAndSelectClientType(row.get("clientType"));
        transactionPage.fillClientInformation(row.get("accountNumber"), row.get("accountType"), row.get("firstName"), row.get("lastName"));
    }

    @And("the final USD calculation should match the rate and amount from Excel")
    public void finishTransaction() {
        transactionPage.proceedTransaction();
        transactionPage.validateTotalDue(row.get("usdValue"), row.get("exchangeFee"), row.get("smallOrderFee"));
        transactionPage.finishTransaction();
        transactionPage.validateTotalDueFinish(row.get("usdValue"), row.get("exchangeFee"), row.get("smallOrderFee"));
    }
}
