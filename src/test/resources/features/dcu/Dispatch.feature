@Dispatch
Feature:  Dispatch

  Background:
    Given I am user "AUTOMATION_USER"

  @Validation
  Scenario: User has a hard copy of a case to dispatch, they decide to reject it and don't fill in a rejection reason
    And I get a "DTEN" case at "DISPATCH" stage
    When I attempt to reject the "DISPATCH" case without reason
    Then an error message is displayed

  @DCUWorkflow
  Scenario: User dispatches a case
    And I get a "DTEN" case at "DISPATCH" stage
    When I "DISPATCH" the case
    Then the case is completed

  @Validation
  Scenario: User must select a radio button when asked if they able to dispatch the case at the Dispatch Stage
    And I get a "DTEN" case at "DISPATCH" stage
    And I click the "Continue" button
    Then an error message should be displayed as I have selected whether the case can be dispatched

  @Validation
  Scenario: User must enter their reason for not being able to dispatch case in the text box at the Dispatch Stage
    And I create a "DTEN" case and move it to the "DISPATCH" stage
    And I load and claim the current case
    And I click the "Finish" button on the "UNABLE TO DISPATCH" page
    Then an error message should be displayed as I have not entered a reason for not dispatching in the text box

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Dispatch stage
    And I get a "DTEN" case at "DISPATCH" stage
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not entered text in the Case Note text box

  @DCUWorkflow @SmokeTests
  Scenario Outline: Case is returned to Private Office Approval stage when rejected by Dispatch Team
    And I get a "<caseType>" case at "Dispatch" stage
    And I reject the case at the "Dispatch" stage
    Then the case should be moved to the "Private Office Approval" stage
    Examples:
      | caseType |
      | MIN  |
      | DTEN |