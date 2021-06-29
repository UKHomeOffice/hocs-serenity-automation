@NRNConfirmation @DCU
Feature: No Response Needed Confirmation

  Background:
    Given I am logged into "DECS" as user "DCU_USER"

  @DCUWorkflow @DCURegression
  Scenario Outline: User confirms the case does not require a response
    When I create a "<caseType>" case and move it to the "No Response Needed Confirmation" stage
    And I load and claim the current case
    And I click the NRN yes radio button
    And I click the "Finish" button
    Then the case should be closed
    Examples:
    | caseType  |
    | MIN       |
    | TRO       |
    | DTEN      |

  @DCUWorkflow @DCURegression
  Scenario Outline: User selects that they dont agree that the case requires no response
    When I create a "<caseType>" case and move it to the "No Response Needed Confirmation" stage
    And I load and claim the current case
    And I click the NRN no radio button
    And I click the "Finish" button
    Then the case should be moved to the "Markup" stage
    Examples:
      | caseType  |
      | MIN       |
      | TRO       |
      | DTEN      |

  @Validation
  Scenario Outline: User tests the validation at the No Response Needed Confirmation stage
    When I create a "<caseType>" case and move it to the "No Response Needed Confirmation" stage
    And I load and claim the current case
    And I trigger the "<errorMessage>" error message at the "No Response Needed Confirmation" stage
    Then the "<errorMessage>" error message is displayed at the "No Response Needed Confirmation" stage
    Examples:
      | caseType  | errorMessage                       |
      | MIN       | No response needed action required |
      | TRO       | No response needed action required |
      | DTEN      | No response needed action required |