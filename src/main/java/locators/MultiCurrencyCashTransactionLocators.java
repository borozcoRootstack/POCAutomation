package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MultiCurrencyCashTransactionLocators {

    @FindBy(id = "product_type")
    public WebElement productTypeSelect;

    @FindBy(id = "add_cur_button")
    public WebElement addCurrencyBtn;

    @FindBy(id = "country_2")
    public WebElement currencyTwoSelect;

    @FindBy(id = "display_amount_2")
    public WebElement amountTwoInput;

    @FindBy(id = "rate_2")
    public WebElement rateTwoInput;

    @FindBy(id = "display_usd_value_2")
    public WebElement uSDValueTwoInput;

    @FindBy(id = "display_fee_2")
    public WebElement exchangeFeeTwoInput;

    @FindBy(id = "ustotal_display")
    public WebElement ustotal_displayInput;
}
