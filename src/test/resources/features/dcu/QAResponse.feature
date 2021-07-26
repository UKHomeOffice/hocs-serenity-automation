@QACase @DCU
Feature: QA Response

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @DCUWorkflow @DCURegression
  Scenario Outline: Case is returned to Initial Draft stage when rejected by QA Response Team
    And I create a "<caseType>" case and move it to the "QA RESPONSE" stage
    And I load and claim the current case
    And I reject the case at the "QA Response" stage
    Then the case should be moved to the "Initial Draft" stage
    Examples:
      | caseType |
      | MIN  |
      | TRO  |
      | DTEN |

    @DCUSmokeTest
    Scenario: User uploads a new primary draft at QA Response stage
      And I create a "DTEN" case and move it to the "QA RESPONSE" stage
      And I load and claim the current case
      And I select to modify the primary draft
      And I upload a "second draft" document
      And I select the "second draft" document as the primary draft
      And I click the "Approve primary draft" button
      Then the case should be moved to the "PRIVATE OFFICE APPROVAL" stage
      And the "second draft" document should be tagged as the primary draft

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the QA Response stage
    And I create a "DTEN" case and move it to the "QA RESPONSE" stage
    And I load and claim the current case
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not entered text in the Case Note text box

  @Validation
  Scenario Outline: User tests the validation at the QA Response stage
    When I create a "<caseType>" case and move it to the "QA Response" stage
    And I load and claim the current case
    And I trigger the "<errorMessage>" error message at the "QA Response" stage
    Then the "<errorMessage>" error message is displayed at the "QA Response" stage
    Examples:
      | caseType  | errorMessage               |
      | MIN       | Actions Required           |
      | TRO       | Rejection Note Required    |