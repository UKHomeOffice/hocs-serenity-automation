@NRNConfirmation @DCU
Feature: No Response Needed Confirmation

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @DCUWorkflow @DCURegression
  Scenario Outline: User confirms the case does not require a response
    And I get a "<caseType>" case at the "No Response Needed Confirmation" stage
    When I agree that there is no need to respond to this correspondence
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "No Response Needed Confirmation" stage
    Examples:
    | caseType  |
    | MIN       |
    | TRO       |
    | DTEN      |

  @DCUWorkflow @DCURegression
  Scenario Outline: User selects that they dont agree that the case requires no response
    And I get a "<caseType>" case at the "No Response Needed Confirmation" stage
    When I disagree that there is no need to respond to this correspondence
    Then the case should be moved to the "Markup" stage
    And the summary should display the owning team as "<markupTeam>"
    And the read-only Case Details accordion should contain all case information entered during the "No Response Needed Confirmation" stage
    Examples:
      | caseType | markupTeam                                       |
      | MIN      | Direct Communications Unit Central Drafting Team |
      | TRO      | Direct Communications Unit Central Drafting Team |
      | DTEN     | Transfers & No10 Team                            |

  @Validation
  Scenario: User tests the validation at the No Response Needed Confirmation stage
    And I get a "MIN" case at the "No Response Needed Confirmation" stage
    And I trigger the "No Response Needed action required" error message at the "No Response Needed Confirmation" stage
    Then the "No Response Needed action required" error message is displayed at the "No Response Needed Confirmation" stage
