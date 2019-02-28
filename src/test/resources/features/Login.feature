Feature: HOCS User is able to login

  Background:
    Given I am on the Home Office Correspondence Login Page

  @Login @critical
  Scenario: I can login to the Home Office Correspondence System
    When I enter my login credentials "EAMON DROKO" and click the login button
    Then I should be taken to the homepage

  @Validation
  Scenario: User must enter valid login credentials on the login screen
    When I enter invalid login credentials on the login screen
    Then an error message should be displayed as the credentials are invalid
