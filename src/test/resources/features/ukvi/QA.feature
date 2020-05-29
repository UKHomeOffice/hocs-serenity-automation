@QA
Feature: QA

  Background:
    Given I log in to DECS
    And I create a "UKVI" case and move it to the "QA" stage
    And I load and claim the current case

  @Navigation
  Scenario: User should be on the UKVI QA Page
    Then the "UKVI QA" page should be displayed

  @Workflow
  Scenario: User completes the QA stage
    When I complete the "QA" stage
    Then the case should be moved to the "Private Office" stage

  Scenario: User escalates the QA case to the workflow manager
    And I select the "Escalate to Workflow Manager" action at QA
    Then the case should be moved to the "QA (Escalated)" stage

  Scenario: User puts a QA case on hold
    And I select the "On Hold" action at QA
    Then the case should be moved to the "QA (On Hold)" stage

  Scenario: User sends a case back to draft at the QA stage
    And I select the "Reject QA at Draft" action at QA
    Then the case should be moved to the "Draft" stage

  Scenario: User sends a case back to triage at the QA stage
    And I select the "Reject QA at Triage" action at QA
    Then the case should be moved to the "Triage" stage

  Scenario: User escalates a QA case to workflow manager and takes the case off escalation
    And I select the "Escalate to Workflow Manager" action at QA
    Then the case should be moved to the "QA (Escalated)" stage
    When I select the "Take Off Hold" action at the QA On Hold stage
    Then the case should be moved to the "QA" stage

  @Validation
  Scenario Outline: User triggers error message is displayed at QA
    And the user triggers the "<errorType>" error message at QA
    Then the "<errorType>" error message should be displayed at QA
    Examples:
    |errorType                        |
    |Actions Required                 |
    |Reject at Triage Reason Required |
    |Reject at Draft Reason Required  |

  @AutoAssignTests
  Scenario Outline: User tests the auto-assign functionality of different actions at QA
    And I logout as the initial user
    And I am user "CAMERON"
    And I load and claim the current case
    And I select the "<actions>" action at QA
    And I load the current case
    Then the case should be allocated to "AUTOMATION_USER"
    And I logout as the initial user
  Examples:
    |actions            |
    |Reject QA at Triage|
    |Reject QA at Draft |
    |Approve            |