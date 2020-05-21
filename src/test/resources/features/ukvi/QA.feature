@CaseQA
Feature: CaseQA

  Background:
    Given I am user "AUTOMATION_USER"
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
  Scenario: Actions required error message is displayed at QA
    And the user should be able to display the "Actions Required" error message at QA