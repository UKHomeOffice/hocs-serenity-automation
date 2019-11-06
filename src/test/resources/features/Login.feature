Feature: HOCS User is able to login

  Background:
    Given I am on the Home Office Correspondence Login Page

  @Login @critical
  Scenario: I can login to the Home Office Correspondence System
    When I enter my login credentials "EAMON DROKO" and click the login button
    Then I should be taken to the homepage

  @Login
  Scenario: User is able to log out from one user and login as another
    And I enter my login credentials "EAMON DROKO" and click the login button
    When I logout as the initial user
    And I enter the login credentials of another user "DCU" and click the login button
    Then I should be logged in as the new user

  @Validation
  Scenario: User must enter valid login credentials on the login screen
    When I enter invalid login credentials on the login screen
    Then an error message should be displayed as the credentials are invalid
