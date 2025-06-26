package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.junit.Assert;

import java.math.BigDecimal;

import static locators.EURToUSDTransactionLocators.*;

public class EURToUSDTransactionPage {
    private final Page page;


    public EURToUSDTransactionPage(Page page) {
        this.page = page;
    }

    public void companyCode(String code) {
        page.fill(COMPANY_CODE_INPUT, "");
        page.fill(COMPANY_CODE_INPUT, code.trim());
        page.keyboard().press("Tab");
    }

    public void locationCode(String code) {
        page.fill(LOCATION_CODE_INPUT, "");
        page.fill(LOCATION_CODE_INPUT, code.trim());
    }

    public void clickLoginLocation() {
        page.click(LOGIN_LOCATION_BUTTON);
    }

    public void clickSellToClient() {
        page.click(SELL_TO_CLIENT_BUTTON);
    }

    public void selectCurrency(String currency) {
        page.selectOption(CURRENCY_SELECT, new SelectOption().setLabel(currency.trim()));
        page.click("body");
    }

    public void enterAmount(String amount) {
        page.locator(DENOMINATIONS).waitFor(new Locator.WaitForOptions()
                .setTimeout(5000)
                .setState(WaitForSelectorState.VISIBLE));

        Locator amountInput = page.locator(AMOUNT_INPUT);
        amountInput.click();
        page.waitForTimeout(500);
        page.keyboard().press("Control+a");
        page.waitForTimeout(200);
        page.keyboard().press("Delete");
        page.waitForTimeout(200);
        page.keyboard().type(amount);
        page.waitForTimeout(500);
        page.keyboard().press("Tab");
        page.waitForTimeout(1500);
    }

    public void validatePopupAppeared() {
        try {
            Locator popupContainer = page.locator(POPUP_CONTAINER);
            popupContainer.waitFor(new Locator.WaitForOptions()
                    .setTimeout(10000)
                    .setState(WaitForSelectorState.VISIBLE));

            Locator okButton = page.locator(POPUP_OK_BUTTON);
            okButton.waitFor(new Locator.WaitForOptions()
                    .setTimeout(5000)
                    .setState(WaitForSelectorState.VISIBLE));

            okButton.click();
            popupContainer.waitFor(new Locator.WaitForOptions()
                    .setTimeout(5000)
                    .setState(WaitForSelectorState.HIDDEN));

        } catch (PlaywrightException e) {
            throw e;
        }
    }

    public void insertRate(String rate) {
        page.waitForTimeout(500);
        page.fill(RATE_INPUT, rate.trim());
    }

    public void insertUSDValue(String value) {
        page.fill(USD_VALUE_INPUT, value.trim());
    }

    public void insertExchangeFee(String fee) {
        page.fill(EXCHANGE_FEE_INPUT, fee.trim());
    }

    public void insertSmallOrderFee(String fee) {
        page.fill(SMALL_ORDER_FEE_INPUT, fee.trim());
    }

    public void insertSpecialRequests(String specialRequests) {
        page.fill(SPECIALS_REQUESTS, specialRequests.trim());
    }

    public void validateTotalDue(String usdValue, String exchangeFee, String smallOrderFee) {
        page.waitForTimeout(2000);
        String total = page.getAttribute(GRAND_TOTAL_DISPLAY, "value").replace("$", "").trim();
        assertTotals(usdValue, exchangeFee, smallOrderFee, total);
    }

    public void validateTotalDueFinish(String usdValue, String exchangeFee, String smallOrderFee) {
        page.waitForTimeout(2000);
        String total = page.textContent(TOTAL_DUE_FINISH).replace("$", "").trim();
        assertTotals(usdValue, exchangeFee, smallOrderFee, total);
    }

    public void clickProceedAndSelectClientType(String type) {
        page.click(PROCEED_BUTTON);
        page.waitForTimeout(2000);
        page.selectOption(CLIENT_TYPE_SELECT, new SelectOption().setLabel(type.trim()));
    }

    public void fillClientInformation(String account, String accountType, String firstName, String lastName) {
        Locator accountInput = page.locator(ACCOUNT_NUMBER_INPUT);
        accountInput.waitFor(new Locator.WaitForOptions().setTimeout(5000).setState(WaitForSelectorState.VISIBLE));

        page.selectOption(ACCOUNT_TYPE_SELECT, new SelectOption().setLabel(accountType.trim()));
        page.waitForTimeout(500);

        accountInput.click();
        page.keyboard().type(account);
        page.waitForTimeout(200);
        accountInput.evaluate("el => el.blur()");
        page.waitForTimeout(500);

        page.fill(CLIENT_FIRSTNAME_INPUT, firstName);
        page.fill(CLIENT_LASTNAME_INPUT, lastName);
    }

    public void proceedTransaction() {
        page.click(COMPLIANCE_OK_BUTTON);
    }

    public void finishTransaction() {
        page.click(SUBMIT_BTN);
    }

    private void assertTotals(String usd, String fee, String smallFee, String actualTotalStr) {
        BigDecimal expected = new BigDecimal(usd).add(new BigDecimal(fee)).add(new BigDecimal(smallFee));
        BigDecimal actual = new BigDecimal(actualTotalStr);
        Assert.assertTrue(
                "Total due mismatch: expected [" + expected + "] but was [" + actual + "]",
                expected.compareTo(actual) == 0
        );
    }
}
