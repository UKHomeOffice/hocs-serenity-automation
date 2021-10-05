@MinSignOff @DCU
Feature: Ministerial Sign-Off

  Background:
    Given I am logged into "CS" as user "DCU_USER"
    And I get a "MIN" case at the "MINISTERIAL SIGN OFF" stage

  @DCUWorkflow @DCURegression
  Scenario: User can select to approve the response
    When I approve the response at the Ministerial Sign Off stage
    Then the case should be moved to the "Dispatch" stage
    And the case should be returned to the drafting team
    And the read-only Case Details accordion should contain all case information entered during the "Ministerial Sign Off" stage

  @DCUWorkflow @DCURegression
  Scenario: User can select to not approve the response
    When I do not approve the response at the Ministerial Sign Off stage
    Then the case should be returned to the "Initial Draft" stage
    And the case should be returned to the drafting team
    And the read-only Case Details accordion should contain all case information entered during the "Ministerial Sign Off" stage
    And a note should be visible in the timeline showing the reason for rejection

  @DCURegression
  Scenario: User can select that the case is not applicable for Miniterial sign-off
    And I select that the case is not applicable for Ministerial sign-off
    And I submit a reason why it is not applicable
    Then the case should be moved to the "Private Office Approval" stage
    And the case should still be owned by the Private Office team
    And the read-only Case Details accordion should contain all case information entered during the "Ministerial Sign Off" stage
    Then a note should be visible in the timeline showing the reason for rejection

  @Validation
  Scenario Outline: User tests the validation at the Ministerial Sign Off stage
    And I trigger the "<errorMessage>" error message at the "Ministerial Sign Off" stage
    Then the "<errorMessage>" error message is displayed at the "Ministerial Sign Off" stage
    Examples:
      | errorMessage                              |
      | Response Approval Required                |
      | Rejection to Draft Note Required          |
      | Rejection to Private Office Note Required |