package locators;

public final class EURToUSDTransactionLocators {
    private EURToUSDTransactionLocators() {}

    // Form inputs
    public static final String COMPANY_CODE_INPUT = "#quick_change_company_code";
    public static final String LOCATION_CODE_INPUT = "#quick_change_location_code";
    public static final String AMOUNT_INPUT = "#display_amount_1";
    public static final String RATE_INPUT = "#rate_1";
    public static final String USD_VALUE_INPUT = "#display_usd_value_1";
    public static final String EXCHANGE_FEE_INPUT = "#display_fee_1";
    public static final String SMALL_ORDER_FEE_INPUT = "#display_order_fee_5";

    // Buttons
    public static final String LOGIN_LOCATION_BUTTON = "//*[@id=\"login_form\"]/table/tbody/tr[2]/td/a/div[2]";
    public static final String SELL_TO_CLIENT_BUTTON = "#sell_btn";
    public static final String POPUP_OK_BUTTON = "#popup_ok";
    public static final String PROCEED_BUTTON = "#submit_button";
    public static final String COMPLIANCE_OK_BUTTON = "#compliance_ok";
    public static final String SUBMIT_BTN = "#submit_btn";

    // Selects
    public static final String CURRENCY_SELECT = "#country_1";
    public static final String CLIENT_TYPE_SELECT = "#client_type";

    public static final String ACCOUNT_TYPE_SELECT = "#acc_type_id";

    // Display elements
    public static final String POPUP_MESSAGE = "#popup_message";
    public static final String GRAND_TOTAL_DISPLAY = "#grandtotal_display";
    public static final String TOTAL_DUE_FINISH = "#table_fee_totals > tbody > tr:nth-child(2) > td:nth-child(2)";

    // Client information
    public static final String ACCOUNT_NUMBER_INPUT = "#account_number";
    public static final String CLIENT_FIRSTNAME_INPUT = "#client_firstname";
    public static final String CLIENT_LASTNAME_INPUT = "#client_lastname";

    // test special_requests special_requests  specialRequests
    public static final String SPECIALS_REQUESTS = "#special_requests";
    public static final String DENOMINATIONS = "#denom_folding_1";

    public static final String POPUP_CONTAINER = "#popup_container";
}
