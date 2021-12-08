@Triage @WCS
Feature: Triage

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I get a "WCS" claim at the "Triage" stage

  @Workflow @WCSRegression
  Scenario: User selects a casework team at Triage stage
    When I select a casework team
    Then the claim should be sent to the correct WCS Casework team

  @Validation
  Scenario: User does not select a casework team
    When I click the "Continue" button
    Then an error message is displayed as I have not selected a caseworking team