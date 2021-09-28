@Drafting @UKVI
Feature: Draft

  Background:
    Given I am logged into "CS" as user "MPAM_USER"

  @Navigation
  Scenario: User should be on the MPAM Draft Page
    When I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    Then the "MPAM Draft" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @UKVIWorkflow @UKVIRegression1
  Scenario: User completes the Draft stage
    When I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    When I complete the "Draft" stage
    Then the case should be moved to the "QA" stage

  @UKVIWorkflow @UKVIRegression1
  Scenario: User moves an Official case from Draft to Dispatch, bypassing QA
    When I create a MPAM case with "Official" as the Reference Type and move it to the "Draft" stage
    And I load and claim the current case
    And I move a Official case from Draft to Dispatch bypassing QA
    Then the case should be moved to the "Awaiting Dispatch" stage

  @UKVIWorkflow @UKVIRegression1
  Scenario: User escalates the draft case to workflow manager
    When I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Workflow Manager"
    Then the case should be moved to the "Draft (Escalated)" stage
    And the case "should" be allocated to me in the summary

  @UKVIWorkflow @UKVIRegression1
  Scenario: User puts the draft case on hold
    When I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "On Hold"
    Then the case should be moved to the "Draft (On Hold)" stage
    And the case "should" be allocated to me in the summary

  @UKVIWorkflow @UKVIRegression1
  Scenario: User takes a Draft On Hold case off hold
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "On Hold"
    When I load and claim the current case
    And I take the Draft (On Hold) case off hold
    Then the case should be moved to the "Draft" stage
    And the case "should" be allocated to me in the summary

  @UKVIWorkflow @UKVIRegression1
  Scenario: User de-escalates a Draft (Escalated) case
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Workflow Manager"
    When I load the current case
    And I select the "De-Escalate" action at the Draft-Escalated stage
    Then the case should be moved to the "Draft" stage
    And the case "should" be allocated to me in the summary

  @UKVIWorkflow @UKVIRegression1
  Scenario: User closes a Draft (Escalated) case
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Workflow Manager"
    When I load the current case
    And I select the "Close Case" action at the Draft-Escalated stage
    And I click the "Close case" button
    Then the case should be closed

  @UKVIWorkflow @UKVIRegression1
  Scenario: User moves case into a Campaign from Draft
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I move the case into a Campaign from the "Draft" stage
    And I load the current case
    Then the case is added to the correct Campaign

  @UKVIWorkflow @UKVIRegression1
  Scenario Outline: User moves cases into Campaigns from draft sub-stages
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "<moveTo>"
    And I load the current case
    And I move the case into a Campaign from the "<draftStage>" stage
    And I load the current case
    Then the case is added to the correct Campaign
    Examples:
    | moveTo                  | draftStage                    |
    | On Hold                 | Draft-On Hold                 |
    | Workflow Manager        | Draft-Escalated               |

  @UKVIRegression1
  Scenario: User rejects a case at Draft back to Triage
    Given I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Triage"
    Then the case should be moved to the "Triage" stage
    And a rejection note should be visible showing the reason for rejection
    And I navigate to the "Dashboard" page
    And I view the MPAM case in the appropriate "Triage" stage workstack
    Then the stage that the case was rejected at should be displayed in the rejected workstack column

  @Validation
  Scenario Outline: User tests validation at the Draft stage
    Given I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I trigger the "<errorType>" error message at the "Draft" stage
    Then the "<errorType>" error message is displayed at the "Draft" stage
    Examples:
      | errorType                        |
      | ACTIONS REQUIRED                 |
      | REJECTION REASON REQUIRED        |
      | ESCALATION REASON REQUIRED       |
      | CAMPAIGN REQUIRED                |