Feature: Login data-driven from Excel

  Scenario: Login with user from Excel row 0
    Given the user navigates to the login page
    When the user logs in using Excel row 0
    Then the user should be redirected to the home page as "testuser6"