@MinSignOff
Feature: Ministerial Sign-Off

  Background:
    Given I log in to DECS
    And I create a "MIN" case and move it to the "MINISTERIAL SIGN OFF" stage
    And I load and claim the current case

  @Validation
  Scenario: User must select a radio button when asked whether or not they approve the response at the Minister Sign Off stage
    When I click the "Continue" button
    Then an error message should be displayed as I have not selected a radio button on the approve response screen

  @Validation
  Scenario: User must enter feedback in a text box if they do not approve the MINISTERIAL SIGN OFF response
    When I click the "Continue" button on the "MINISTERIAL SIGN OFF FEEDBACK RESPONSE" page
    Then an error message should be displayed as I have not entered feedback in the text box

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the MINISTERIAL SIGN OFF stage
    When I click the add button when creating a case note
    Then an error message should be displayed as I have not entered text in the Case Note text box

  @DCUWorkflow @SmokeTests
  Scenario: DCU MIN Case returned to Initial Draft stage when rejected by the Minister
    And I reject the case at the "MINISTERIAL SIGN OFF" stage
    Then the case should be moved to the "INITIAL DRAFT" stage