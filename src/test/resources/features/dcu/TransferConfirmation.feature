@TransferConfirmation
Feature: TransferConfirmation

  Background:
    Given I log in to DECS
    And I create a "MIN" case and move it to the "Transfer Confirmation" stage
    And I load and claim the current case

  @DCUWorkflow @SmokeTests
  Scenario: User confirms the case does not require a response
    When I click the confirm transfer yes radio button
    And I click the "Finish" button
    Then the case should be closed

  @DCUWorkflow @SmokeTests
  Scenario: User selects that they dont agree that the case requires no response
    When I click the confirm transfer no radio button
    And I click the "Finish" button
    Then the case should be moved to the "Markup" stage

  @Validation
  Scenario: User must select whether the agree the case should be transferred
    When I click the "Finish" button
    Then an error message should be displayed as I have not selected a response on the Transfer Confirmation screen