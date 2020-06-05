@Drafting
Feature: Drafting

  Background:
    Given I log in to DECS
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case

  @Navigation
  Scenario: User should be on the MPAM Draft Page
    Then the "MPAM Draft" page should be displayed

  @Workflow
  Scenario: User completes the Draft stage
    When I complete the "Draft" stage
    Then the case should be moved to the "QA" stage