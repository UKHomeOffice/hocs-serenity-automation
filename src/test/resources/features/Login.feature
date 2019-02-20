Feature: HOCS User is able to login

  Background:
    Given I am on the Home Office Correspondence Login Page

  @Login @critical
  Scenario: I can login to the Home Office Correspondence System
    When I enter my username "EAMON DROKO" in the username field
    And I enter my password "EAMON PASS" in the password field
    And Select the login button
    Then I will hit the Home Page
