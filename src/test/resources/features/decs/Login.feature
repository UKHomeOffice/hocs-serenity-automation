@Login @DECS
Feature: Login

  Background:
    Given I am on the DECS Login Page
    And I am prompted to log in

  @Regression @Smoke
  Scenario: I can login to DECS
    When I enter the login credentials for user "DECS_USER" and click the login button
    Then I should be taken to the homepage
    And I should be logged in as the user "DECS_USER"

  @Regression
  Scenario: User is able to log out from one user and login as another
    And I enter the login credentials for user "DECS_USER" and click the login button
    When I logout of the application
    And I enter the login credentials of another user "DCU_USER" and click the login button
    Then I should be logged in as the user "DCU_USER"

  @Validation
  Scenario: User must enter valid login credentials on the login screen
    When I enter invalid login credentials on the login screen
    Then an error message should be displayed as the credentials are invalid
