Feature: HOCS User is able to draft a response

  Background:
    Given I log in as the designated user

  @InitialDraft @Validation
  Scenario: User decides the case is not for them, does not complete a rejection note
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    When I select a case "should not" be answered by my team
    And I "do not complete" the rejection note
    Then an error message appears instructing me to add rejection reasons

  @InitialDraft @SmokeTests
  Scenario: User responds by Phone and fills out call details to complete the case
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    Given I select a case "should" be answered by my team
    And I select to reply by "phone"
    When I "complete" the call details
    And I select "no" to choosing another Response Type
    Then I am returned to my home screen
    And the case is completed

  @InitialDraft @Validation
  Scenario: Phone response does not have info in free text field
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    Given I select a case "should" be answered by my team
    And I select to reply by "phone"
    And I "do not complete" the call details
    Then I see an error message instructing me to enter call notes

  @InitialDraft @Workflow
  Scenario: A user selects not to offline QA
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    When I select a case "should" be answered by my team
    And I select to reply by "email"
    And I upload a "draft" document
    And I select "no" to QA offline
    Then I am returned to my home screen
    And the "DCU MIN" case should be moved to the "QA RESPONSE" stage

  @InitialDraft @Workflow @SmokeTests
  Scenario: User selects offline QA
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    When I select a case "should" be answered by my team
    And I select to reply by "email"
    And I upload a "draft" document
    And I select "yes" to QA offline
    And I select "Eamon" as the offline QA
    Then I am returned to my home screen
    And the "DCU MIN" case should be moved to the "PRIVATE OFFICE APPROVAL" stage

  @InitialDraft @Validation
  Scenario: User must select a radio button when asked whether correspondence can be answered by their team at the Draft stage
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    And I click the "CONTINUE" button
    Then an error message should be displayed as I have not selected radio buttons on this screen

  @InitialDraft @Validation
  Scenario: User must enter the reason that their team cannot answer a case in the text box at the Draft stage
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    And I click the "FINISH" button on the "CASE REJECTION" page
    Then an error message should be displayed as I have not entered a reason in the text box


  @InitialDraft @Validation
  Scenario: User must select a radio button when asked how they intend to respond at the Draft stage
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    And I click the "CONTINUE" button on the "HOW DO YOU INTEND TO RESPOND" page
    Then an error message should be displayed as I have not selected a response on this screen

  @InitialDraft @Validation
  Scenario: User must summarise their call in the text box at the Draft stage after selecting phone response
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    And I click the "CONTINUE" button on the "SUMMARISE YOUR CALL" page
    Then an error message should be displayed as I have not summarised the call

  @InitialDraft @Validation
  Scenario: User must add a primary draft document at the Draft stage
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    And I click the "CONTINUE" button on the "PRIMARY DRAFT DOCUMENT" page
    Then an error message should be displayed as I have not added a primary draft document

  @InitialDraft @Validation
  Scenario: User must select the Draft document type and add a document on the add document screen at the Draft stage
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    And I click the "ADD" button on the "ADD DOCUMENT" page
    Then an error message should be displayed as I have not selected a document type and added a document

  @InitialDraft @Validation
  Scenario: User must select a radio button when asked whether they want to QA the case offline
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    And I click the "CONTINUE" button on the "DO YOU WANT TO QA OFFLINE" page
    Then an error message should be displayed as I have not selected whether the case should be QA offline or not

  @InitialDraft @Validation
  Scenario: User must select the user that has done the Offline QA from the drop down at the Draft stage
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    And I click the "FINISH" button on the "WHO HAS DONE THE QA OFFLINE" page
    Then an error message should be displayed as I have not selected the user that did the offline QA

  @InitialDraft @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Draft stage
    And I get a "DCU MIN" case at "INITIAL DRAFT" stage
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX"

  @InitialDraft @SmokeTests
  Scenario Outline: Case is returned to Markup stage when rejected at Initial Draft stage
    And I get a "<caseType>" case at "Initial Draft" stage
    And I reject the case at the "Initial Draft" stage
    Then the "<caseType>" case should be moved to the "Markup" stage
    Examples:
      | caseType |
      | DCU MIN  |
      | DCU TRO  |
      | DCU N10  |