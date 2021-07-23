@TransferConfirmation @DCU
Feature: TransferConfirmation

  Background:
    Given I am logged into "CS" as user "DCU_USER"

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
  Scenario Outline: User tests the validation at the Transfer Confirmation stage
    When I create a "<caseType>" case and move it to the "Transfer Confirmation" stage
    And I load and claim the current case
    And I trigger the "<errorMessage>" error message at the "Transfer Confirmation" stage
    Then the "<errorMessage>" error message is displayed at the "Transfer Confirmation" stage
    Examples:
    | caseType  | errorMessage                                      |
    | MIN       | Should this case be transferred response required |
    | TRO       | Should this case be transferred response required |
    | DTEN      | Should this case be transferred response required |