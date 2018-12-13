Feature: HOCS User is able to login

  Background:
    Given I am on the Home Office Correspondance Login Page

  @Login @critical
  Scenario: I can login to the Home Office Correspondance System
    When I enter my username "DANNY LARGE" in the username field
    And I enter my password "DANNY PASS" in the password field
    And Select the login button
    Then I will hit the Home Page
