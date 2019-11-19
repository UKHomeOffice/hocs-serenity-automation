Feature: User decides how cases should be handled at Minister Sign Off stage

  Background:
    Given I log in as the designated user
    When I get a "DCU MIN" case at "MINISTERIAL SIGN OFF" stage

  @Navigation
  Scenario: Clicking the cancel button on the allocate case screen at the Minster Sign Off stage should take the user back
  to the dashboard
    And I click the cancel button
    Then I should be taken to the homepage

  @Validation
  Scenario: User must select a radio button when asked whether or not they approve the response at the Minister Sign Off stage
    And I click the "CONTINUE" button
    Then an error message should be displayed as I have not selected a radio button on the approve response screen

  @Validation
  Scenario: User must enter feedback in a text box if they do not approve the Minister Sign Off response
    And I click the "CONTINUE" button on the "MINISTER SIGN OFF FEEDBACK RESPONSE" page
    Then an error message should be displayed as I have not entered feedback in the text box

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Minister Sign Off stage
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX"