@NRNConfirmation @DCU
Feature: No Response Needed Confirmation

  Background:
    Given I log in to DECS
    And I create a "MIN" case and move it to the "No Response Needed Confirmation" stage
    And I load and claim the current case

  @DCUWorkflow @DCURegression
  Scenario: User confirms the case does not require a response
    When I click the NRN yes radio button
    And I click the "Finish" button
    Then the case should be closed

  @DCUWorkflow @DCURegression
  Scenario: User selects that they dont agree that the case requires no response
    When I click the NRN no radio button
    And I click the "Finish" button
    Then the case should be moved to the "Markup" stage

  @Validation
  Scenario: User must select whether the agree with NRN
    When I click the "Finish" button
    Then an error message should be displayed as I have not selected a response on the NRN Confirmation screen

