@Drafting @MPAM
Feature: Drafting

  Background:
    Given I log in to DECS

  @Navigation
  Scenario: User should be on the MPAM Draft Page
    When I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    Then the "MPAM Draft" page should be displayed
    And the header tags in the HTML of the page are properly structured

  @MPAMWorkflow @MPAMRegression
  Scenario: User completes the Draft stage
    When I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    When I complete the "Draft" stage
    Then the case should be moved to the "QA" stage

  @MPAMWorkflow @MPAMRegression
  Scenario: User moves an Official case from Draft to Dispatch, bypassing QA
    When I create a MPAM case  with "UKVI" as the Business Area and "Official" as the Reference Type and move it to the "Draft" stage
    And I load and claim the current case
    And I move a Official case from Draft to Dispatch bypassing QA
    Then the case should be moved to the "Awaiting Dispatch" stage

  @MPAMWorkflow @MPAMRegression
  Scenario: User escalates the draft case to workflow manager
    When I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Workflow Manager"
    Then the case should be moved to the "Draft (Escalated)" stage
    And the case should be allocated to me in the summary

  @MPAMWorkflow @MPAMRegression
  Scenario: User puts the draft case on hold
    When I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "On Hold"
    Then the case should be moved to the "Draft (On Hold)" stage
    And the case should be allocated to me in the summary

  @MPAMWorkflow @MPAMRegression
  Scenario: User takes a Draft On Hold case off hold
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "On Hold"
    When I load and claim the current case
    And I take the Draft (On Hold) case off hold
    Then the case should be moved to the "Draft" stage
    And the case should be allocated to me in the summary

  @MPAMWorkflow @MPAMRegression
  Scenario: User de-escalates a Draft (Escalated) case
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Workflow Manager"
    When I load and claim the current case
    And I de-escalate the Draft (Escalated) case
    Then the case should be moved to the "Draft" stage
    And the case should be allocated to me in the summary

  @MPAMWorkflow @MPAMRegression
  Scenario: User closes a Draft (Escalated) case
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Workflow Manager"
    When I load and claim the current case
    And I select to close the Draft (Escalated) case
    And I submit a reason to close the case at Draft (Escalated) stage
    Then the case should be closed
    And a closure note should be visible showing the reason for closing the case

  @MPAMWorkflow @MPAMRegression
  Scenario: User requests a contribution at Draft stage
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    When I send the Draft case to "Contribution Requested"
    Then the contribution request deadline should be visible in the "Draft" workstack
    And the case should be moved to the "Draft (Contribution Requested)" stage
    And the case should be allocated to me in the summary
    And the contribution request deadline should be visible in the summary
    And a contribution request note should be visible showing the description of the request

  @MPAMWorkflow @MPAMRegression
  Scenario: User selects that the contribution has been received at Draft (Contribution Requested) stage
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    When I send the Draft case to "Contribution Requested"
    And I load and claim the current case
    When I select the "Contributions received" action at Draft (Contribution Requested) stage
    Then the case should be moved to the "Draft" stage
    And the case should be allocated to me in the summary

  @MPAMWorkflow @MPAMRegression
  Scenario: User escalates a case at Draft (Contribution Requested) stage
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    When I send the Draft case to "Contribution Requested"
    And I load and claim the current case
    When I select the "Escalate to Workflow Manager" action at Draft (Contribution Requested) stage
    Then the case should be moved to the "Draft (Escalated)" stage
    And the case should be allocated to me in the summary

  @Validation
  Scenario Outline: User triggers error message to be displayed at Draft
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    When the user triggers the "<errorType>" error message at Draft by not entering the correct information
    Then  the "<errorType>" error message should be displayed at Draft
    Examples:
      | errorType                                 |
      | Actions Required                          |
      | Response Channel Required                 |
      | Contribution Request Deadline Required    |
      | Contribution Request Description Required |

  @Campaigns
  Scenario: User moves case into a Campaign from Draft
    And I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I move the case into a Campaign from the "Draft" stage
    And I load the current case
    Then the case is added to the correct Campaign

  @Campaigns
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
    | Contribution Requested  | Draft-Contribution Requested  |