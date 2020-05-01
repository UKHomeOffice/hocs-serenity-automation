@CaseCreation
Feature: CaseCreation

  Background:
    Given I am user "AUTOMATION_USER"
    And I create a "UKVI" case and move it to the "Case Creation" stage
    And I load and claim the current case

  Scenario: User should be on the UKVI Data Input Page
    Then the "UKVI Data Input" page should be displayed

  Scenario: User adds an MP correspondent at Case Creation stage

  Scenario: User adds a member of public correspondent at Case Creation stage

  Scenario: User removes a correspondent

  Scenario: User edits an existing correspondent

  Scenario: User adds a second correspondent and selects them as the primary correspondent

  Scenario: User attempts to progress without answering required questions

  Scenario: User attempts to progress without adding a correspondent

  Scenario : User completed case creation with specific Business Area and Reference Type



