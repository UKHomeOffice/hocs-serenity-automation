@CaseQA
Feature: CaseQA

  Background:
    Given I am user "AUTOMATION_USER"
    And I create a "UKVI" case and move it to the "Case QA" stage
    And I load and claim the current case

  @Navigation
  Scenario: User should be on the UKVI QA Page
    Then the "UKVI QA" page should be displayed

  @Workflow
  Scenario: User completes the QA stage
    When I complete the "Case QA" stage
    Then the case should be moved to the "Case Private Office" stage