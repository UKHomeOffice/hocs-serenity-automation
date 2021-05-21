@TransferConfirmation @DCU
Feature: TransferConfirmation

  Background:
    Given I am logged into "DECS" as user "DCU_USER"

  @DCUWorkflow @DCURegression
  Scenario Outline: User confirms the case does not require a response
    When I create a "<caseType>" case and move it to the "Transfer Confirmation" stage
    And I load and claim the current case
    And I click the confirm transfer yes radio button
    And I click the "Finish" button
    Then the case should be closed
    Examples:
    | caseType  |
    | MIN       |
    | TRO       |
    | DTEN      |

  @DCUWorkflow @DCURegression
  Scenario Outline: User selects that they dont agree that the case requires no response
    When I create a "<caseType>" case and move it to the "Transfer Confirmation" stage
    And I load and claim the current case
    And I click the confirm transfer no radio button
    And I click the "Finish" button
    Then the case should be moved to the "Markup" stage
    Examples:
      | caseType  |
      | MIN       |
      | TRO       |
      | DTEN      |

  @Validation
  Scenario Outline: User must select whether the agree the case should be transferred
    When I create a "<caseType>" case and move it to the "Transfer Confirmation" stage
    And I load and claim the current case
    And I click the "Finish" button
    Then an error message should be displayed as I have not selected a response on the Transfer Confirmation screen
    Examples:
      | caseType  |
      | MIN       |
      | TRO       |
      | DTEN      |