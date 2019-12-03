Feature: QA Case #this test can be reused for both the private office and minister sign off stages

  Background:
    Given I log in as the designated user
    And I get a "DCU N10" case at "QA RESPONSE" stage

  @QACase @Validation
  Scenario: User must select a radio button to indicate whether they approve the QA response
    And I click the "CONTINUE" button
    Then an error message should be displayed as I have not selected a radio button on the QA approve response screen

  @QACase @Validation @HOCS-310
  Scenario: User reviews draft, rejects it and does not provide a rejection reason
    When I attempt to reject the "QA RESPONSE" case without reason
    Then an error message should be displayed as I have not entered feedback in the text box for the disapproved QA response

  @QACase @Validation
  Scenario: User must enter text in the text box when creating a Case note at the QA Response stage
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX"