Feature: Perform complex multi-currency cash transaction

  Background:
    Given the user navigates to the login page
    When the user logs in using Excel row 0

  Scenario: Execute multi-currency transaction with edit and fee validation
    Given the user selects company location and starts transaction using Excel row 0
    And the user selects product type as "CASH"
    And the user enters a transaction total between 500 and 750 using two currencies
    And the user deletes one currency and re-enters it
    And the user selects "Priority" shipping
    When the user submits the transaction
    And the user clicks "Edit" and verifies all information is still present
    And the user finishes the transaction
    Then the fees should be correct for location 245/999 and appear in the preview and receipt
    And the receipt should display all relevant transaction details