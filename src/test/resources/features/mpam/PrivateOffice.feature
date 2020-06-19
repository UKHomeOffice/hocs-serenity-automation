@CasePrivateOffice
Feature: PrivateOffice

  Background:
    Given I log in to DECS
    And I create a "MPAM" case and move it to the "Private Office" stage
    And I load and claim the current case

  @Navigation
  Scenario: User selects the Dispatched action
    When I select the "Dispatched" action at Private Office stage
    Then the "MPAM Private Office" page should be displayed


  @Navigation
  Scenario: User selects the Draft rejected by Private Office action
    When I select the "Draft rejected by Private Office" action at Private Office stage
    Then the "MPAM Reject Print for Signage" page should be displayed
    And the rejection reason entry box should be visible

  @SmokeTests
  Scenario: User can see which response channel was selected at Draft stage
    Then I can see the previous selected response channel is still selected

  @MPAMWorkflow @SmokeTests
  Scenario: User rejects the case back to drafting
    When I select the "Draft rejected by Private Office" action at Private Office stage
    And I submit a reason to reject the case back to Draft stage
    Then the case should be moved to the "Draft" stage
    And a rejection note should be visible showing the reason for rejection

  @MPAMWorkflow @SmokeTests
  Scenario: User enters a date of dispatch and closes the case
    When I select the "Dispatched" action at Private Office stage
    And I enter a date of dispatch and confirm to close the case
    Then the case should be closed

  Scenario Outline: User triggers error message to be displayed at Private Office
    When the user triggers the "<errorType>" error message at Private Office by not entering the correct information
    Then then the "<errorType>" error message should be displayed at Private Office
    Examples:
      | errorType        |
      | Actions Required |
      | Dispatched Date  |