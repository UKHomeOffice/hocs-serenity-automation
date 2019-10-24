Feature: User can view correct case details at all stages

  Background:
    Given I am user "EAMON"

  @Accordion
  Scenario: Data Input accordion should contain the same information entered at the Case Creation stage
    Given I create a single "DCU MIN" case
    And I go to the case from the successful case creation screen
    When I select the "Data Input" button of the accordion
    Then the information shown should match what I entered at the "Case Creation" stage

  @Accordion
  Scenario: Markup accordion should contain the same information entered at the Data Input stage
    Given I create a single "DCU MIN" case
    And I allocate the case to myself via the successful case creation screen
    And I move that case to the "Markup" stage
    And I load the current case
    When I select the "Data Input" button of the accordion
    Then the information shown should match what I entered at the "Data Input" stage

  @Accordion
  Scenario: Initial Draft accordion should contain the same information entered at the Markup stage
    Given I create a single "DCU MIN" case
    And I allocate the case to myself via the successful case creation screen
    And I move that case to the "Initial Draft" stage
    And I load the current case
    When I select the "Markup" button of the accordion
    Then the information shown should match what I entered at the "Markup" stage

  @Accordion
  Scenario: QA Response accordion should contain the same information entered at the Initial Draft stage
    Given I create a single "DCU MIN" case
    And I allocate the case to myself via the successful case creation screen
    And I move that case to the "QA Response" stage
    And I load the current case
    When I select the "Initial Draft" button of the accordion
    Then the information shown should match what I entered at the "Initial Draft" stage

  @Accordion
  Scenario: Private Office Approval accordion should contain the same information entered at the QA Response stage
    Given I create a single "DCU MIN" case
    And I allocate the case to myself via the successful case creation screen
    And I move that case to the "Private Office Approval" stage
    And I load the current case
    When I select the "QA Response" button of the accordion
    Then the information shown should match what I entered at the "QA Response" stage

  @Accordion
  Scenario: Ministerial Sign Off accordion should contain the same information entered at the Private Office Approval stage
    Given I create a single "DCU MIN" case
    And I allocate the case to myself via the successful case creation screen
    And I move that case to the "Ministerial Sign Off" stage
    And I load the current case
    When I select the "Private Office Approval" button of the accordion
    Then the information shown should match what I entered at the "Private Office Approval" stage

  @Accordion
  Scenario: Transfer to No10 accordion should contain the same information entered at the Initial Draft stage
    Given I create a single "DCU MIN" case
    And I allocate the case to myself via the successful case creation screen
    And I move that case to the "Transfer to No10" stage
    And I load the current case
    When I select the "Ministerial Sign Off" button of the accordion
    Then the information shown should match what I entered at the "Ministerial Sign Off" stage