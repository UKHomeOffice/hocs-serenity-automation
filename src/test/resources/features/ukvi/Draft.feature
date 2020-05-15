@Drafting
Feature: Drafting

  Background:
    Given I am user "AUTOMATION_USER"
    And I create a "UKVI" case and move it to the "Draft" stage
    And I load and claim the current case

  @Navigation
  Scenario: User should be on the UKVI Draft Page
    Then the "UKVI Draft" page should be displayed

  @Workflow
  Scenario: User completes the Draft stage
    When I complete the "Draft" stage
    Then the case should be moved to the "QA" stage