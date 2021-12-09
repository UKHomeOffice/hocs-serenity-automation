@QA @MPAM
Feature: QA

  Background:
    Given I am logged into "CS" as user "MPAM_USER"

  @Navigation
  Scenario: User should be on the MPAM QA Page
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    Then the "MPAM QA" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @MPAMWorkflow @MPAMRegression2
  Scenario: User completes the QA stage for a Ministerial case
    And I create a MPAM case with "Ministerial" as the Reference Type and move it to the "QA" stage
    And I load and claim the current case
    When I complete the "QA" stage
    Then the case should be moved to the "Private Office" stage

  @MPAMWorkflow @MPAMRegression2
  Scenario: User completes the QA stage for a Official case
    And I create a MPAM case with "Official" as the Reference Type and move it to the "QA" stage
    And I load and claim the current case
    When I complete the "QA" stage
    Then the case should be moved to the "Awaiting Dispatch" stage

  @MPAMWorkflow @MPAMRegression2
  Scenario: User escalates the QA case to the workflow manager
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Escalate to Workflow Manager" action at QA
    And I submit a reason to escalate the case at QA stage
    Then the case should be moved to the "QA (Escalated)" stage

  @MPAMWorkflow @MPAMRegression2
  Scenario: User puts a QA case on hold
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Put on hold" action at QA
    Then the case should be moved to the "QA (On Hold)" stage

  @MPAMWorkflow @MPAMRegression2
  Scenario: User sends a case back to draft at the QA stage
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Rejected, move back to drafting" action at QA
    And I submit a reason to reject the case back to drafting
    Then the case should be moved to the "Draft" stage
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And I navigate to the "Dashboard" page
    And I view the MPAM case in the appropriate "Draft" stage workstack
    Then the stage that the case was rejected at should be displayed in the rejected workstack column

  @MPAMWorkflow @MPAMRegression2
  Scenario: User sends a case back to triage at the QA stage
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Rejected, move back to triage" action at QA
    And I submit a reason to reject the case back to triage
    Then the case should be moved to the "Triage" stage
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And I navigate to the "Dashboard" page
    And I view the MPAM case in the appropriate "Triage" stage workstack
    Then the stage that the case was rejected at should be displayed in the rejected workstack column

  @MPAMWorkflow @MPAMRegression2
  Scenario: User takes a QA (On Hold) case off hold
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Put on hold" action at QA
    And I load the current case
    When I select the "Take off hold" action at the QA On Hold stage
    Then the case should be moved to the "QA" stage
    And the case "should" be allocated to me in the summary

  @MPAMWorkflow @MPAMRegression2
  Scenario: User de-escalates a QA (Escalated) case
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Escalate to Workflow Manager" action at QA
    And I submit a reason to escalate the case at QA stage
    And I load the current case
    When I select the "Escalation Complete" action at the QA Escalated stage
    Then the case should be moved to the "QA" stage
    And the case "should" be allocated to me in the summary

  @MPAMWorkflow @MPAMRegression2
  Scenario: User closes a QA (Escalated) case
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Escalate to Workflow Manager" action at QA
    And I submit a reason to escalate the case at QA stage
    When I load the current case
    And I select to close the QA (Escalated) case
    And I click the "Close case" button
    Then the case should be closed

  @AutoAssignTests @MPAMRegression2
  Scenario Outline: User tests the auto-assign functionality of different actions at QA using multiple user accounts
    And I create a MPAM case with "Official" as the Reference Type and move it to the "QA" stage
    And I load and claim the current case
    And I record the user who completed the previous stages
    And I logout of the application
    And I am logged into "CS" as user "CAMERON"
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

  @MPAMWorkflow @MPAMRegression2
  Scenario: User moves a case into a Campaign from the QA stage
    And I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    When I move the case into a Campaign from the "QA" stage
    And I load the current case
    Then the case is added to the correct Campaign

  @MPAMWorkflow @MPAMRegression2
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

  #To be added to regression once HOCS-3268 is developed
  Scenario: User can request Secretariat clearance for a UKVI/BF/IE Ministerial case at QA
    When I create a MPAM case with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Request Secretariat clearance" action at QA
    And I add a Clearance Request to the case at QA
    Then the case should be moved to the "QA (Secretariat Clearance Requested)" stage

    #To be added to regression once HOCS-3268 is developed
  Scenario: User can approve a clearance request at QA (Secretariat Clearance Requested)
    When I create a MPAM case with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Request Secretariat clearance" action at QA
    And I add a Clearance Request to the case at QA
    And I load the current case
    And I enter the clearance received date for the clearance request
    And I "Approve" the Clearance Request at the QA Secretariat Clearance Requested stage
    Then the case should be moved to the "Private Office" stage

  #To be added to regression once HOCS-3268 is developed
  Scenario: User can reject a clearance request at QA (Secretariat Clearance Requested)
    When I create a MPAM case with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Request Secretariat clearance" action at QA
    And I add a Clearance Request to the case at QA
    And I load the current case
    And I enter the clearance received date for the clearance request
    And I "Reject" the Clearance Request at the QA Secretariat Clearance Requested stage
    Then the case should be moved to the "Draft" stage
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case

    #To be added to regression once HOCS-3268 is developed
  Scenario: User can cancel a clearance request at QA (Secretariat Clearance Requested)
    When I create a MPAM case with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "QA" stage
    And I load and claim the current case
    And I select the "Request Secretariat clearance" action at QA
    And I add a Clearance Request to the case at QA
    And I load the current case
    And I enter the clearance received date for the clearance request
    And I "Cancel" the Clearance Request at the QA Secretariat Clearance Requested stage
    Then the case should be moved to the "QA" stage

  @Validation
  Scenario Outline: User tests validation at the QA stage
    When I create a "MPAM" case and move it to the "QA" stage
    And I load and claim the current case
    And I trigger the "<errorType>" error message at the "QA" stage
    Then the "<errorType>" error message is displayed at the "QA" stage
    Examples:
      | errorType                           |
      | ACTIONS REQUIRED                    |
      | REJECTION TO TRIAGE REASON REQUIRED |
      | REJECTION TO DRAFT REASON REQUIRED  |
      | ESCALATION REASON REQUIRED          |
      | CAMPAIGN REQUIRED                   |