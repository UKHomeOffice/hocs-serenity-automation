Feature: HOCS User is able to login

  Background:
    Given I am on the Home Office Correspondence Login Page
    And I am prompted to log in

  @Login @SmokeTests
  Scenario: I can login to the Home Office Correspondence System
    When I enter the login credentials for user "AUTOMATION_USER" and click the login button
    Then I should be taken to the homepage
    And I should be logged in as the user "AUTOMATION_USER"

#  @Login @Smoketests - currently broken due to keycloack limitations on non-prod environments
  Scenario: User is able to log out from one user and login as another
    And I enter the login credentials for user "AUTOMATION_USER" and click the login button
    When I logout as the initial user
    And I enter the login credentials of another user "DCU" and click the login button
    Then I should be logged in as the user "DCU"

  @Login @Validation
  Scenario: User must enter valid login credentials on the login screen
    When I enter invalid login credentials on the login screen
    Then an error message should be displayed as the credentials are invalid
