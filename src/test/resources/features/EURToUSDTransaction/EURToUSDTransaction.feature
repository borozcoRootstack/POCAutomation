Feature: Execute transaction flow based on Excel data

  Background:
    Given the user navigates to the login page
    When the user logs in using Excel row 0

  Scenario: Execute transaction from Excel
    Given the user selects company location and starts transaction using Excel row 0
    And the system should round and adjust the amount as per Excel
    And validate rate and fees based on Excel data
    And special fields should be visible in the receipt from Excel
    And the final USD calculation should match the rate and amount from Excel