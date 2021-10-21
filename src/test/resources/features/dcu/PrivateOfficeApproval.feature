@POSignOff @DCU
Feature: Private Office Approval

  Background:
    Given I am logged into "CS" as user "DCU_USER"
    
  @DCUWorkflow @DCURegression
  Scenario Outline: User can return a case to the Initial Draft stage
    And I get a "<caseType>" case at the "Private Office Approval" stage
    And I reject the case at the Private Office Approval stage
    Then the case should be moved to the "Initial Draft" stage
    And the case should be returned to the drafting team
    And the read-only Case Details accordion should contain all case information entered during the "Private Office Approval" stage
    And a note should be visible in the timeline showing the reason for rejection
    Examples:
      | caseType |
      | MIN  |
      | DTEN |

  @DCUWorkflow @DCURegression
  Scenario: User can approve a MIN case at the Private Office Approval stage
    And I get a "MIN" case at the "Private Office Approval" stage
    When I approve the case at the Private Office Approval stage
    Then the case should be moved to the "Ministerial Sign Off" stage
    And the case should still be owned by the Private Office team
    And the read-only Case Details accordion should contain all case information entered during the "Private Office Approval" stage

  @DCUWorkflow @DCURegression
  Scenario: User can approve a DTEN case at the Private Office Approval stage
    And I get a "DTEN" case at the "Private Office Approval" stage
    When I approve the case at the Private Office Approval stage
    Then the case should be moved to the "Dispatch" stage
    And the summary should display the owning team as "Transfers & No10 Team"
    And the read-only Case Details accordion should contain all case information entered during the "Private Office Approval" stage
    
  @DCURegression
  Scenario: User can change the minister for the case
    And I get a "MIN" case at the "Private Office Approval" stage
    When I select to change minister
    And I select "Home Secretary" as the new Private Office team
    And I submit a reason for changing Private Office team
    Then the summary should display the owning team as "Home Secretary"
    And the read-only Case Details accordion should contain all case information entered during the "Private Office Approval" stage

  @DCURegression
  Scenario: User can change the Primary topic at the Private Office stage
    And I get a "MIN" case at the "Private Office Approval" stage
    And I override the Primary Topic of the case at the Private Office stage to "Breeding of research animals"
    Then the summary should display the owning team as "Minister for Lords"
    And the summary should display "Breeding of research animals" for "Primary topic"
    And the read-only Case Details accordion should contain all case information entered during the "Private Office Approval" stage
    And the reason for changing the primary topic of the case should be added as a case note in the timeline
    
  Scenario: User overrides the PO team at Markup and moves the case to the PO stage
    And I get a "MIN" case at the "Markup" stage
    And I complete the Markup stage overriding the "Private Office" team to "Home Secretary"
    And I advance the case to the Private Office Approval stage
    Then the summary should display the owning team as "Home Secretary"
    And the summary should display "Home Secretary" for "Override Private Office Team"

  @Validation
  Scenario Outline: User tests the validation at the Private Office Approval stage
    And I get a "<caseType>" case at the "Private Office Approval" stage
    And I trigger the "<errorMessage>" error message at the "Private Office Approval" stage
    Then the "<errorMessage>" error message is displayed at the "Private Office Approval" stage
    Examples:
      | caseType  | errorMessage                          |
      | DTEN      | Response Approval Required            |
      | DTEN      | Rejection Note Required               |
      | MIN       | Override Private Office Team Required |
      | MIN       | Reason for Change Minister Required   |
      | MIN       | Reason for Topic Change Required      |