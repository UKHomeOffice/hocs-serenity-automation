@AccordionDCU  @DCU @OtherTests
Feature: Case Details Accordion

  Background:
    Given I log in to "DECS" as user "DECS_USER"
    And I create a single "MIN" case

  Scenario: Data Input accordion should contain the same information entered at the Case Creation stage
    And I go to the case from the successful case creation screen
    When I select the "Data Input" button of the accordion
    Then the information shown should match what I entered at the "Case Creation" stage

  Scenario: Markup accordion should contain the same information entered at the Data Input stage
    And I allocate the case to myself via the successful case creation screen
    And I move that case to the "Markup" stage and record all entered information
    And I load the current case
    When I select the "Data Input" button of the accordion
    Then the information shown should match what I entered at the "Data Input" stage

  Scenario: Initial Draft accordion should contain the same information entered at the Markup stage
    And I allocate the case to myself via the successful case creation screen
    And I move that case to the "Initial Draft" stage and record all entered information
    And I load the current case
    When I select the "Markup" button of the accordion
    Then the information shown should match what I entered at the "Markup" stage

  Scenario: QA Response accordion should contain the same information entered at the Initial Draft stage
    And I allocate the case to myself via the successful case creation screen
    And I move that case to the "QA Response" stage and record all entered information
    And I load the current case
    When I select the "Initial Draft" button of the accordion
    Then the information shown should match what I entered at the "Initial Draft" stage

  Scenario: Private Office Approval accordion should contain the same information entered at the QA Response stage
    And I allocate the case to myself via the successful case creation screen
    And I move that case to the "Private Office Approval" stage and record all entered information
    And I load the current case
    When I select the "QA Response" button of the accordion
    Then the information shown should match what I entered at the "QA Response" stage

  Scenario: Ministerial Sign Off accordion should contain the same information entered at the Private Office Approval stage
    And I allocate the case to myself via the successful case creation screen
    And I move that case to the "Ministerial Sign Off" stage and record all entered information
    And I load the current case
    When I select the "Private Office Approval" button of the accordion
    Then the information shown should match what I entered at the "Private Office Approval" stage

  Scenario: Transfer to No10 accordion should contain the same information entered at the Ministerial Sign Off stage
    And I allocate the case to myself via the successful case creation screen
    And I move that case to the "Transfer to No10" stage and record all entered information
    And I load the current case
    When I select the "Ministerial Sign Off" button of the accordion
    Then the information shown should match what I entered at the "Ministerial Sign Off" stage