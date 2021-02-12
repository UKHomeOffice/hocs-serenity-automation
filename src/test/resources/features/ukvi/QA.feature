@QA @UKVI
Feature: QA

  Background:
    Given I log in to "DECS" as user "DECS_USER"

  @Navigation
  Scenario: User should be on the MPAM QA Page
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    Then the "MPAM QA" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @UKVIWorkflow @UKVIRegression
  Scenario: User completes the QA stage for a Ministerial case
    And I create a MPAM case  with "Ministerial" as the Reference Type and move it to the "QA" stage
    And I load and claim the current case
    When I complete the "QA" stage
    Then the case should be moved to the "Private Office" stage

  @UKVIWorkflow @UKVIRegression
  Scenario: User completes the QA stage for a Official case
    And I create a MPAM case  with "Official" as the Reference Type and move it to the "QA" stage
    And I load and claim the current case
    When I complete the "QA" stage
    Then the case should be moved to the "Awaiting Dispatch" stage

  @UKVIWorkflow @UKVIRegression
  Scenario: User escalates the QA case to the workflow manager
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Escalate to Workflow Manager" action at QA
    And I submit a reason to escalate the case at QA stage
    Then the case should be moved to the "QA (Escalated)" stage

  @UKVIWorkflow @UKVIRegression
  Scenario: User puts a QA case on hold
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Put on hold" action at QA
    Then the case should be moved to the "QA (On Hold)" stage

  @UKVIWorkflow @UKVIRegression
  Scenario: User sends a case back to draft at the QA stage
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Rejected, move back to drafting" action at QA
    And I submit a reason to reject the case back to drafting
    Then the case should be moved to the "Draft" stage
    And a rejection note should be visible showing the reason for rejection
    And I navigate to the "home" page
    And I view the MPAM case in the appropriate "Draft" stage workstack
    Then the stage that the case was rejected at should be displayed in the rejected workstack column

  @UKVIWorkflow @UKVIRegression
  Scenario: User sends a case back to triage at the QA stage
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Rejected, move back to triage" action at QA
    And I submit a reason to reject the case back to triage
    Then the case should be moved to the "Triage" stage
    And a rejection note should be visible showing the reason for rejection
    And I navigate to the "home" page
    And I view the MPAM case in the appropriate "Triage" stage workstack
    Then the stage that the case was rejected at should be displayed in the rejected workstack column

  @UKVIWorkflow @UKVIRegression
  Scenario: User takes a QA (On Hold) case off hold
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Put on hold" action at QA
    And I load and claim the current case
    When I select the "Take off hold" action at the QA On Hold stage
    Then the case should be moved to the "QA" stage
    And the case "should" be allocated to me in the summary

  @UKVIWorkflow @UKVIRegression
  Scenario: User de-escalates a QA (Escalated) case
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Escalate to Workflow Manager" action at QA
    And I submit a reason to escalate the case at QA stage
    And I load and claim the current case
    When I select the "Escalation Complete" action at the QA Escalated stage
    Then the case should be moved to the "QA" stage
    And the case "should" be allocated to me in the summary

  @UKVIWorkflow @UKVIRegression
  Scenario: User closes a QA (Escalated) case
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Escalate to Workflow Manager" action at QA
    And I submit a reason to escalate the case at QA stage
    When I load and claim the current case
    And I select to close the QA (Escalated) case
    And I click the "Close case" button
    Then the case should be closed

  @Validation
  Scenario Outline: User triggers error message is displayed at QA
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And the user triggers the "<errorType>" error message at QA
    Then the "<errorType>" error message should be displayed at QA
    Examples:
      | errorType                        |
      | Actions Required                 |
      | Reject at Triage Reason Required |
      | Reject at Draft Reason Required  |

  @AutoAssignTests @UKVIRegression
  Scenario Outline: User tests the auto-assign functionality of different actions at QA using multiple user accounts
    And I create a MPAM case  with "Official" as the Reference Type and move it to the "QA" stage
    And I load and claim the current case
    And I record the user who completed the previous stages
    And I logout of the application
    And I log in to "DECS" as user "CAMERON"
    And I load and claim the current case
    And I complete the "<actions>" action at QA
    And I load the current case
    Then the case should be allocated to the original user
    And I logout of the application
    Examples:
      | actions                         |
      | Rejected, move back to triage   |
      | Rejected, move back to drafting |
      | Approve                         |

  @Campaigns
  Scenario: User moves a case into a Campaign from the QA stage
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    When I move the case into a Campaign from the "QA" stage
    And I load the current case
    Then the case is added to the correct Campaign

  @Campaigns
  Scenario Outline: User moves cases into Campaigns from the QA sub-stages
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    When I complete the "<action>" action at QA
    And I load the current case
    And I move the case into a Campaign from the "<qaStage>" stage
    And I load the current case
    Then the case is added to the correct Campaign
    Examples:
    | action                        | qaStage       |
    | Put On Hold                   | QA-On Hold    |
    | Escalate To Workflow Manager  | QA-Escalated  |