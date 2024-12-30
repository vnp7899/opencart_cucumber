Feature: Login Data Driven with Excel

  @regression
  Scenario Outline: Login with Data Driven Excel
    Given the user navigates to login page
    Then the user should be redirected to My Account page by passing the email and password with excel row "<row_index>"

    Examples: 
      | row_index |
      |         1 |
      |         2 |
      |         3 |
