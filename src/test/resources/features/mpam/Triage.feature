@Triage
Feature: Triage

  Background:
    Given I log in to DECS
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case

  @Navigation
  Scenario: User should be on the MPAM Triage Page
    Then the "MPAM Triage" page should be displayed

  @Navigation
  Scenario: User should be on the Enquiry Subject Page
    When I click the "Set enquiry subject/reason" link
    Then the "Enquiry subject" page should be displayed

  @Navigation
  Scenario: User should be on the Enquiry Reason Page
    When I click the "Set enquiry subject/reason" link
    And I select an enquiry subject and continue
    Then the "Enquiry reason" page should be displayed

  Scenario: User can see the selected enquiry subject and reason on the MPAM Triage page
    When I click the "Set enquiry subject/reason" link
    And I select an enquiry subject and continue
    And I select an enquiry reason and continue
    Then the set enquiry subject and reason should be displayed on the MPAM Triage page

  Scenario: User views the Business Units for different Business Areas
    And I select to change the Business Area
    And I record the current options for Business Area
    When I change the Business Area of the case to "Windrush"
    Then the options for Business Area should change

  @MPAMWorkflow @SmokeTests
  Scenario: User completes the Triage stage
    When I complete the "Triage" stage
    Then the case should be moved to the "Draft" stage

  @MPAMWorkflow @SmokeTests
  Scenario: User puts the Triage case On Hold
    When I send the Triage case to "On Hold"
    Then the case should be moved to the "Triage (On Hold)" stage
    And the case should be allocated to me in the summary

  @Workflow @SmokeTests
  Scenario: User escalates the Triage case to the workflow manager
    When I send the Triage case to "Workflow Manager"
    Then the case should be moved to the "Triage (Escalated)" stage

  @MPAMWorkflow @SmokeTests
  Scenario: User takes a Triage On Hold case off hold
    And I send the Triage case to "On Hold"
    And I load and claim the current case
    When I take the Triage (On Hold) case off hold
    Then the case should be moved to the "Triage" stage
    And the case should be allocated to me in the summary

  @MPAMWorkflow @SmokeTests
  Scenario: User de-escalates a Triage (Escalated) case
    When I send the Triage case to "Workflow Manager"
    And I load and claim the current case
    When I de-escalate the Triage (Escalated) case
    Then the case should be moved to the "Triage" stage
    And the case should be allocated to me in the summary

  @MPAMWorkflow @SmokeTests
  Scenario: User closes a Triage (Escalated) case
    And I send the Triage case to "Workflow Manager"
    When I load and claim the current case
    And I select to close the Triage (Escalated) case
    And I submit a reason to close the case at Triage (Escalated) stage
    Then the case should be closed
    And a closure note should be visible showing the reason for closing the case

  @MPAMWorkflow @SmokeTests
  Scenario: User requests a contribution at triage stage
    When I send the Triage case to "Contribution Requested"
    Then the contribution request deadline should be visible in the "Triage" workstack
    And the case should be moved to the "Triage (Contribution Requested)" stage
    And the case should be allocated to me in the summary
    And the contribution request deadline should be visible in the summary
    And a contribution request note should be visible showing the description of the request

  @MPAMWorkflow @SmokeTests
  Scenario: User selects that the contribution has been received at Triage (Contribution Requested) stage
    When I send the Triage case to "Contribution Requested"
    And I load and claim the current case
    When I select the "Contributions received" action at Triage (Contribution Requested) stage
    Then the case should be moved to the "Triage" stage
    And the case should be allocated to me in the summary

  @MPAMWorkflow @SmokeTests
  Scenario: User escalates a case at Triage (Contribution Requested) stage
    When I send the Triage case to "Contribution Requested"
    And I load and claim the current case
    When I select the "Escalate to Workflow Manager" action at Triage (Contribution Requested) stage
    Then the case should be moved to the "Triage (Escalated)" stage
    And the case should be allocated to me in the summary

  @Validation
  Scenario Outline: User triggers error message to be displayed at Triage
    When the user triggers the "<errorType>" error message at Triage by not entering the correct information
    Then  the "<errorType>" error message should be displayed at Triage
    Examples:
      | errorType                                 |
      | Actions Required                          |
      | Business Unit Required                    |
      | Enquiry Subject Required                  |
      | Contribution Request Deadline Required    |
      | Contribution Request Description Required |
