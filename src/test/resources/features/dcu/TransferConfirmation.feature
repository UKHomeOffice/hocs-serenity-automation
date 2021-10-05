@TransferConfirmation @DCU
Feature: TransferConfirmation

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @DCUWorkflow @DCURegression
  Scenario Outline: User confirms the case does not require a response
    And I get a "<caseType>" case at the "Transfer Confirmation" stage
    When I confirm the case should be transferred
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Transfer Confirmation" stage
    Examples:
    | caseType  |
    | MIN       |
    | TRO       |
    | DTEN      |

  @DCUWorkflow @DCURegression
  Scenario Outline: User selects that they dont agree that the case requires no response
    And I get a "<caseType>" case at the "Transfer Confirmation" stage
    When I confirm the case should not be transferred
    Then the case should be moved to the "Markup" stage
    And the summary should display the owning team as "<markupTeam>"
    And the read-only Case Details accordion should contain all case information entered during the "Transfer Confirmation" stage
    Examples:
      | caseType | markupTeam                                       |
      | MIN      | Direct Communications Unit Central Drafting Team |
      | TRO      | Direct Communications Unit Central Drafting Team |
      | DTEN     | Transfers & No10 Team                            |


  @Validation
  Scenario: User tests the validation at the Transfer Confirmation stage
    And I get a "MIN" case at the "Transfer Confirmation" stage
    And I trigger the "Should this case be transferred response required" error message at the "Transfer Confirmation" stage
    Then the "Should this case be transferred response required" error message is displayed at the "Transfer Confirmation" stage