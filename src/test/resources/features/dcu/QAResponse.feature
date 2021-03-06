@QACase @DCU
Feature: QA Response

  Background:
    Given I log in to "DECS" as user "DCU_USER"

  @Validation
  Scenario: User must select a radio button to indicate whether they approve the QA response
    And I create a "DTEN" case and move it to the "QA RESPONSE" stage
    And I load and claim the current case
    And I click the "Continue" button
    Then an error message should be displayed as I have not selected a radio button on the QA approve response screen

  @Validation
  Scenario: User reviews draft, rejects it and does not provide a rejection reason
    And I create a "DTEN" case and move it to the "QA RESPONSE" stage
    And I load and claim the current case
    When I attempt to reject the "QA RESPONSE" case without reason
    Then an error message should be displayed as I have not entered feedback in the text box for the disapproved QA response

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the QA Response stage
    And I create a "DTEN" case and move it to the "QA RESPONSE" stage
    And I load and claim the current case
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not entered text in the Case Note text box

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