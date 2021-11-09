@QACase @DCU
Feature: QA Response

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @DCUWorkflow @DCURegression
  Scenario Outline: A MIN or DTEN case is moved to Private Office Approval stage when the Primary Draft is approved by the QA Response Team
    And I get a "<caseType>" case at the "QA Response" stage
    When I approve the Primary Draft
    Then the case should be moved to the "Private Office Approval" stage
    And the case should be owned by the Private Office team
    And the read-only Case Details accordion should contain all case information entered during the "QA Response" stage
    Examples:
      | caseType |
      | MIN      |
      | DTEN     |

  @DCUWorkflow @DCURegression
  Scenario: A TRO case is moved to Disptach stage when the Primary Draft is approved by the QA Response Team
    And I get a "TRO" case at the "QA Response" stage
    When I approve the Primary Draft
    Then the case should be moved to the "Dispatch" stage
    And the case should still be owned by the drafting team
    And the read-only Case Details accordion should contain all case information entered during the "QA Response" stage

  @DCUWorkflow @DCURegression
  Scenario Outline: Case is returned to Initial Draft stage when the Primary Draft is rejected by the QA Response Team
    And I get a "<caseType>" case at the "QA Response" stage
    When I reject the case at the QA Response stage
    Then the case should be returned to the "Initial Draft" stage
    And the case should be returned to the drafting team
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the read-only Case Details accordion should contain all case information entered during the "QA Response" stage
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @DCURegression
  Scenario: User uploads a new primary draft at QA Response stage
    And I create a "DTEN" case and move it to the "QA RESPONSE" stage
    And I load and claim the current case
    When I select to modify the primary draft
    And I upload a "replacement draft" document
    And I select the "replacement draft" document as the primary draft
    And I approve the new primary draft document
    Then the case should be moved to the "PRIVATE OFFICE APPROVAL" stage
    And the selected document should be tagged as the primary draft

  @Validation
  Scenario Outline: User tests the validation at the QA Response stage
    When I create a "<caseType>" case and move it to the "QA Response" stage
    And I load and claim the current case
    And I trigger the "<errorMessage>" error message at the "QA Response" stage
    Then the "<errorMessage>" error message is displayed at the "QA Response" stage
    Examples:
      | caseType | errorMessage            |
      | MIN      | Actions Required        |
      | TRO      | Rejection Note Required |