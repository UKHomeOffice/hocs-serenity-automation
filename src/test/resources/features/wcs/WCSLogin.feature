@Login @WCS
Feature: Login

  Background:
    Given I am on the Windrush Compensation Scheme Login Page
    And I am prompted to log in


  @WCSRegression
  Scenario: I can login to the Windrush Correspondence System
    When I enter the login credentials for user "WCS_USER" and click the login button
    Then I should be taken to the homepage
    And I should be logged in as the user "WCS_USER"

  @WCSRegression
  Scenario: User is able to log out from one user and login as another
    And I enter the login credentials for user "WCS_USER" and click the login button
    When I logout of the application
    And I enter the login credentials of another user "TEST_USER_1" and click the login button
    Then I should be logged in as the user "TEST_USER_1"

  @Validation
  Scenario: User must enter valid login credentials on the login screen
    When I enter invalid login credentials on the login screen
    Then an error message should be displayed as the credentials are invalid
