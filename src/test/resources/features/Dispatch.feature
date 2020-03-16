Feature:  HOCS User is able to Dispatch a Response

  Background:
    Given I am user "AUTOMATION_USER"

  @Dispatch @Validation
  Scenario: User has a hard copy of a case to dispatch, they decide to reject it and don't fill in a rejection reason
    And I get a "DTEN" case at "DISPATCH" stage
    When I attempt to reject the "DISPATCH" case without reason
    Then an error message is displayed

  @Dispatch @Workflow
  Scenario: User dispatches a case
    And I get a "DTEN" case at "DISPATCH" stage
    When I "DISPATCH" the case
    Then the case is completed

  @Dispatch @Validation
  Scenario: User must select a radio button when asked if they able to dispatch the case at the Dispatch Stage
    And I get a "DTEN" case at "DISPATCH" stage
    And I click the "CONTINUE" button
    Then an error message should be displayed as I have selected whether the case can be dispatched

  @Dispatch @Validation
  Scenario: User must enter their reason for not being able to dispatch case in the text box at the Dispatch Stage
    And I create a "DTEN" case and move it to the "DISPATCH" stage
    And I load and claim the current case
    And I click the "FINISH" button on the "UNABLE TO DISPATCH" page
    Then an error message should be displayed as I have not entered a reason for not dispatching in the text box

  @Dispatch @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Dispatch stage
    And I get a "DTEN" case at "DISPATCH" stage
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX"

  @Dispatch @Workflow @SmokeTests
  Scenario Outline: Case is returned to Private Office Approval stage when rejected by Dispatch Team
    And I get a "<caseType>" case at "Dispatch" stage
    And I reject the case at the "Dispatch" stage
    Then the "<caseType>" case should be moved to the "Private Office Approval" stage
    Examples:
      | caseType |
      | MIN  |
      | DTEN |