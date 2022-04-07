
Feature: End to End Security Test

  Scenario: Comp end to end
    Given I am on the Correspondence System Login Page for security testing
    When I enter the login credentials for user "DECS_USER" and click the login button for security testing
    And I create a "comp" case for security testing

  Scenario: FOI end to end
    Given I am on the Correspondence System Login Page for security testing
    When I enter the login credentials for user "DECS_USER" and click the login button for security testing
    And I create a foi case for security testing

  @security
  Scenario: DCU end to end
    Given I am on the Correspondence System Login Page for security testing
    When I enter the login credentials for user "DECS_USER" and click the login button for security testing
    And I create a DCU case for security testing