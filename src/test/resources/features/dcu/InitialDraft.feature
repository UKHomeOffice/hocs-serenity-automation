@InitialDraft @DCU
Feature: Initial Draft

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @DCURegression
  Scenario Outline: User responds by Phone and fills out call details to complete the case
    And I create a "<caseType>" case and move it to the "INITIAL DRAFT" stage
    And I load and claim the current case
    Given I select a case "should" be answered by my team
    And I select to reply by "phone"
    When I "complete" the call details
    And I select "no" to choosing another Response Type
    Then I am returned to the dashboard
    And the case should be closed
    Examples:
      | caseType  |
      | MIN       |
      | TRO       |

  @DCUWorkflow @DCURegression
  Scenario Outline: A user selects not to offline QA a MIN or TRO case
    And I create a "<caseType>" case and move it to the "INITIAL DRAFT" stage
    And I load and claim the current case
    When I select a case "should" be answered by my team
    And I select to reply by "email"
    And I upload a "draft" document
    And I select the "draft" document as the primary draft
    And I click the "continue" button
    And I select "no" to QA offline
    Then I am returned to the dashboard
    And the case should be moved to the "QA RESPONSE" stage
    And the "draft" document should be tagged as the primary draft
    Examples:
      | caseType  |
      | MIN       |
      | TRO       |

  @DCURegression
  Scenario: User selects not to offline QA a DTEN case
    And I create a "DTEN" case and move it to the "INITIAL DRAFT" stage
    And I load and claim the current case
    When I select a case "should" be answered by my team
    And I upload a "draft" document
    And I select the "draft" document as the primary draft
    And I click the "continue" button
    And I select "no" to QA offline
    Then I am returned to the dashboard
    And the case should be moved to the "QA RESPONSE" stage
    And the "draft" document should be tagged as the primary draft

  @DCUWorkflow @DCURegression
  Scenario: User selects offline QA for a MIN case
    And I create a "MIN" case and move it to the "INITIAL DRAFT" stage
    And I load and claim the current case
    When I select a case "should" be answered by my team
    And I select to reply by "email"
    And I upload a "draft" document
    And I select the "draft" document as the primary draft
    And I click the "continue" button
    And I select "yes" to QA offline
    And I select "DECS_USER" as the offline QA
    Then I am returned to the dashboard
    And the case should be moved to the "Private Office Approval" stage
    And the "draft" document should be tagged as the primary draft

  @DCUWorkflow @DCURegression
  Scenario: User selects offline QA for a DTEN case
    And I create a "DTEN" case and move it to the "INITIAL DRAFT" stage
    And I load and claim the current case
    When I select a case "should" be answered by my team
    And I upload a "draft" document
    And I select the "draft" document as the primary draft
    And I click the "continue" button
    And I select "yes" to QA offline
    And I select "DECS_USER" as the offline QA
    Then I am returned to the dashboard
    And the case should be moved to the "Private Office Approval" stage
    And the "draft" document should be tagged as the primary draft

  @DCUWorkflow @DCURegression
  Scenario: User selects offline QA for a TRO case
    And I create a "TRO" case and move it to the "INITIAL DRAFT" stage
    And I load and claim the current case
    When I select a case "should" be answered by my team
    And I select to reply by "email"
    And I upload a "draft" document
    And I select the "draft" document as the primary draft
    And I click the "continue" button
    And I select "yes" to QA offline
    And I select "DECS_USER" as the offline QA
    Then I am returned to the dashboard
    And the case should be moved to the "Dispatch" stage
    And the "draft" document should be tagged as the primary draft

  @Validation
  Scenario: User must select the Draft document type and add a document on the add document screen at the Draft stage
    And I create a "MIN" case and move it to the "INITIAL DRAFT" stage
    And I load and claim the current case
    And I click the "Add" button on the "ADD DOCUMENT" page
    Then an error message should be displayed as I have not selected a document type and added a document

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Draft stage
    And I create a "MIN" case and move it to the "INITIAL DRAFT" stage
    And I load and claim the current case
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not entered text in the Case Note text box

  @DCUWorkflow @DCURegression
  Scenario Outline: Case is returned to Markup stage when rejected at Initial Draft stage
    And I create a "<caseType>" case and move it to the "INITIAL DRAFT" stage
    And I load and claim the current case
    And I reject the case at the "Initial Draft" stage
    Then the case should be moved to the "Markup" stage
    Examples:
      | caseType |
      | MIN  |
      | TRO  |
      | DTEN  |

  @Validation
  Scenario Outline: User tests the validation at the Initial Draft stage
    When I create a "<caseType>" case and move it to the "Initial Draft" stage
    And I load and claim the current case
    And I trigger the "<errorMessage>" error message at the "Initial Draft" stage
    Then the "<errorMessage>" error message is displayed at the "Initial Draft" stage
    Examples:
    | caseType  | errorMessage                                |
    | MIN       | CAN THIS BE ANSWERED BY YOUR TEAM REQUIRED  |
    | TRO       | RESPONSE CHANNEL REQUIRED                   |
    | DTEN      | PRIMARY DRAFT DOCUMENT REQUIRED             |
    | MIN       | QA THIS OFFLINE REQUIRED                    |
    | TRO       | WHO HAS DONE QA OFFLINE REQUIRED            |