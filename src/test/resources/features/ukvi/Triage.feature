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