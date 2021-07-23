@MinSignOff @DCU
Feature: Ministerial Sign-Off

  Background:
    Given I am logged into "CS" as user "DCU_USER"
    And I create a "MIN" case and move it to the "MINISTERIAL SIGN OFF" stage
    And I load and claim the current case

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the MINISTERIAL SIGN OFF stage
    When I click the add button when creating a case note
    Then an error message should be displayed as I have not entered text in the Case Note text box

  @DCUWorkflow @DCURegression
  Scenario: DCU MIN Case returned to Initial Draft stage when rejected by the Minister
    And I reject the case at the "MINISTERIAL SIGN OFF" stage
    Then the case should be moved to the "INITIAL DRAFT" stage

  @DCURegression
  Scenario: User rejects a case at Ministerial Sign Off and returns it to Private Office Approval
    And I return the case at Ministerial Sign Off to Private Office Approval
    Then the case should be moved to the "Private Office Approval" stage
    And I navigate to the "Dashboard" page
    And I load and claim the current case
    Then a rejection note should be visible showing the reason for rejection

  @Validation
  Scenario Outline: User tests the validation at the Ministerial Sign Off stage
    And I trigger the "<errorMessage>" error message at the "Ministerial Sign Off" stage
    Then the "<errorMessage>" error message is displayed at the "Ministerial Sign Off" stage
    Examples:
      | errorMessage                              |
      | Response Approval Required                |
      | Rejection to Draft Note Required          |
      | Rejection to Private Office Note Required |