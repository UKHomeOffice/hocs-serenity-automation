@Dispatch @DCU
Feature: Dispatch

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @DCUWorkflow @DCURegression
  Scenario Outline: User dispatches a case
    And I create a "<caseType>" case and move it to the "Dispatch" stage
    And I load and claim the current case
    When I complete the dispatch stage
    Then the case should be closed
    Examples:
    | caseType  |
    | MIN       |
    | TRO       |
    | DTEN      |

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Dispatch stage
    And I create a "DTEN" case and move it to the "Dispatch" stage
    And I load and claim the current case
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not entered text in the Case Note text box

  @DCUWorkflow @DCURegression
  Scenario Outline: User can return a case to Private Office Approval stage
    And I create a "<caseType>" case and move it to the "Dispatch" stage
    And I load and claim the current case
    And I reject the case at the "Dispatch" stage
    Then the case should be moved to the "Private Office Approval" stage
    Examples:
      | caseType |
      | MIN      |
      | DTEN     |

  @DCURegression
  Scenario: User returns a TRO case to initial draft when rejected at Dispatch
    And I create a "TRO" case and move it to the "Dispatch" stage
    And I load and claim the current case
    And I reject the case at the "Dispatch" stage
    Then the case should be moved to the "Initial Draft" stage

  @Validation
  Scenario Outline: User tests the validation at the Dispatch stage
    When I create a "<caseType>" case and move it to the "Dispatch" stage
    And I load and claim the current case
    And I trigger the "<errorMessage>" error message at the "Dispatch" stage
    Then the "<errorMessage>" error message is displayed at the "Dispatch" stage
    Examples:
    | caseType  | errorMessage                |
    | MIN       | DISPATCH RESPONSE REQUIRED  |
    | TRO       | REJECTION NOTE REQUIRED     |