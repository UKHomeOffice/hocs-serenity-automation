Feature: User decides how cases should be handled at QA stage

  Background:
    Given I log in as the designated user

  @QACase @Validation
  Scenario: User must select a radio button to indicate whether they approve the QA response
    And I get a "DTEN" case at "QA RESPONSE" stage
    And I click the "CONTINUE" button
    Then an error message should be displayed as I have not selected a radio button on the QA approve response screen

  @QACase @Validation
  Scenario: User reviews draft, rejects it and does not provide a rejection reason
    And I get a "DTEN" case at "QA RESPONSE" stage
    When I attempt to reject the "QA RESPONSE" case without reason
    Then an error message should be displayed as I have not entered feedback in the text box for the disapproved QA response

  @QACase @Validation
  Scenario: User must enter text in the text box when creating a Case note at the QA Response stage
    And I get a "DTEN" case at "QA RESPONSE" stage
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX"

  @QACase @Workflow @SmokeTests
  Scenario Outline: Case is returned to Initial Draft stage when rejected by QA Response Team
    And I get a "<caseType>" case at "QA Response" stage
    And I reject the case at the "QA Response" stage
    Then the "<caseType>" case should be moved to the "Initial Draft" stage
    Examples:
      | caseType |
      | MIN  |
      | TRO  |
      | DTEN |