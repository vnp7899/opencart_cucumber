Feature: Account Registration

  @regression
  Scenario: Successful Account Registration
    Given the user navigates to registration page
    When user eneters all the necessary information intobelow fields
      | firstname | virat      |
      | lastname  | kohli      |
      | telephone | 7899456123 |
      | password  | vnp@7899   |
    And user selects the privacy policy
    And user clicks on continue button
    Then the user account should get created successfully
