@Login @DECS
Feature: Login

  Background:
    Given I am on the Home Office Correspondence Login Page
    And I am prompted to log in

  @Regression @test
  Scenario: I can login to the Home Office Correspondence System
    When I enter the login credentials for user "AUTOMATION_USER" and click the login button
    Then I should be taken to the homepage
    And I should be logged in as the user "AUTOMATION_USER"

  @Regression
  Scenario: User is able to log out from one user and login as another
    And I enter the login credentials for user "AUTOMATION_USER" and click the login button
    When I logout of the application
    And I enter the login credentials of another user "CASEY" and click the login button
    Then I should be logged in as the user "CASEY"

  @Validation
  Scenario: User must enter valid login credentials on the login screen
    When I enter invalid login credentials on the login screen
    Then an error message should be displayed as the credentials are invalid
