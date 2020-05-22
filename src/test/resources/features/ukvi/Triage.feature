@Triage
Feature: Triage

  Background:
    Given I am user "AUTOMATION_USER"
    And I create a "UKVI" case and move it to the "Triage" stage
    And I load and claim the current case

  @Navigation
  Scenario: User should be on the UKVI Triage Page
    Then the "UKVI Triage" page should be displayed

  @Workflow
  Scenario: User completes the Triage stage
    When I complete the "Triage" stage
    Then the case should be moved to the "Draft" stage

  Scenario: User puts the Triage case On Hold
    When I send the Triage case to "On Hold"
    Then the case should be moved to the "Triage (On Hold)" stage

  @Validation
  Scenario: Actions required error message is displayed at triage
    And the user triggers the "Actions Required" error message at triage
    Then the "Actions Required" error message should be displayed at triage

  @Validation
  Scenario: Business unit required error message is displayed at triage
    And the user triggers the "Business Unit" error message at triage
    Then the "Business Unit" error message should be displayed at triage