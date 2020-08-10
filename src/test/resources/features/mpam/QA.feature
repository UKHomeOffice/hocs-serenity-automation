@QA @MPAM
Feature: QA

  Background:
    Given I log in to DECS
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case

  @Navigation
  Scenario: User should be on the MPAM QA Page
    Then the "MPAM QA" page should be displayed

  @MPAMWorkflow @MPAMSmokeTests
  Scenario: User completes the QA stage
    When I complete the "QA" stage
    Then the case should be moved to the "Private Office" stage

  @MPAMWorkflow @MPAMSmokeTests
  Scenario: User escalates the QA case to the workflow manager
    And I select the "Escalate to Workflow Manager" action at QA
    Then the case should be moved to the "QA (Escalated)" stage

  @MPAMWorkflow @MPAMSmokeTests
  Scenario: User puts a QA case on hold
    And I select the "Put on hold" action at QA
    Then the case should be moved to the "QA (On Hold)" stage

  @MPAMWorkflow @MPAMSmokeTests
  Scenario: User sends a case back to draft at the QA stage
    And I select the "Rejected, move back to drafting" action at QA
    And I submit a reason to reject the case back to drafting
    Then the case should be moved to the "Draft" stage
    And a rejection note should be visible showing the reason for rejection

  @MPAMWorkflow @MPAMSmokeTests
  Scenario: User sends a case back to triage at the QA stage
    And I select the "Rejected, move back to triage" action at QA
    And I submit a reason to reject the case back to triage
    Then the case should be moved to the "Triage" stage
    And a rejection note should be visible showing the reason for rejection

  @MPAMWorkflow @MPAMSmokeTests
  Scenario: User takes a QA (On Hold) case off hold
    And I select the "Put on hold" action at QA
    And I load and claim the current case
    When I select the "Take off hold" action at the QA On Hold stage
    Then the case should be moved to the "QA" stage
    And the case should be allocated to me in the summary

  @MPAMWorkflow @MPAMSmokeTests
  Scenario: User de-escalates a QA (Escalated) case
    And I select the "Escalate to Workflow Manager" action at QA
    And I load and claim the current case
    When I select the "Escalation Complete" action at the QA Escalated stage
    Then the case should be moved to the "QA" stage
    And the case should be allocated to me in the summary

  @MPAMWorkflow @MPAMSmokeTests
  Scenario: User closes a QA (Escalated) case
    And I select the "Escalate to Workflow Manager" action at QA
    When I load and claim the current case
    And I select to close the QA (Escalated) case
    And I submit a reason to close the case at QA (Escalated) stage
    Then the case should be closed
    And a closure note should be visible showing the reason for closing the case

  @Validation
  Scenario Outline: User triggers error message is displayed at QA
    And the user triggers the "<errorType>" error message at QA
    Then the "<errorType>" error message should be displayed at QA
    Examples:
      | errorType                        |
      | Actions Required                 |
      | Reject at Triage Reason Required |
      | Reject at Draft Reason Required  |

  @AutoAssignTests @MPAMSmokeTests
  Scenario Outline: User tests the auto-assign functionality of different actions at QA using multiple user accounts
    And I record the user who completed the previous stages
    And I logout of the application
    And I log in to DECS as user "CAMERON"
    And I load and claim the current case
    And I select the "<actions>" action at QA
    And I load the current case
    Then the case should be allocated to the original user
    And I logout of the application
    Examples:
      | actions                         |
      | Rejected, move back to triage   |
      | Rejected, move back to drafting |
      | Approve                         |