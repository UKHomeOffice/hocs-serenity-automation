Feature: User decides how cases should be handled at Minister Sign Off stage

  Background:
    Given I log in as the designated user
    And I get a "MIN" case at "MINISTERIAL SIGN OFF" stage

  @MinSignOff @Validation
  Scenario: User must select a radio button when asked whether or not they approve the response at the Minister Sign Off stage
    When I click the "CONTINUE" button
    Then an error message should be displayed as I have not selected a radio button on the approve response screen

  @MinSignOff @Validation
  Scenario: User must enter feedback in a text box if they do not approve the Minister Sign Off response
    When I click the "CONTINUE" button on the "MINISTER SIGN OFF FEEDBACK RESPONSE" page
    Then an error message should be displayed as I have not entered feedback in the text box

  @MinSignOff @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Minister Sign Off stage
    When I click the add button when creating a case note
    Then an error message should be displayed as I have not "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX"

  @MinSignOff @Workflow @SmokesTests
  Scenario: DCU MIN Case returned to Initial Draft stage when rejected by the Minister
    And I reject the case at the "MINISTERIAL SIGN OFF" stage
    Then the "MIN" case should be moved to the "INITIAL DRAFT" stage