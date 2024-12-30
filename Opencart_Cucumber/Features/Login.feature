Feature: Login with valid credentials

  #@sanity @regression
  #Scenario: Successfull login with valid credentials
  #Given the user navigates to login page
  #When user eneters email and password as "vnp789900@gmail.com","vnp@7899"
  #And clicks on login button
  #Then user should be redirected to MyAccount Page
  @regression
  Scenario Outline: Login Data Driven
    Given the user navigates to login page
    When user eneters email and password as "<email>","<password>"
    And clicks on login button
    Then user should be redirected to MyAccount Page

    Examples: 
      | email               |  | password |
      | pavanol@gmail.com   |  | test123  |
      | vnp789900@gmail.com |  | vnp@7899 |
