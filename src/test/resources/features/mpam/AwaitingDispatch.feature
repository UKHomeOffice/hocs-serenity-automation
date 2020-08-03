@AwaitingDispatch
Feature: Awaiting Dispatch

  Background:
    Given I log in to DECS
    And I create a "MPAM" case and move it to the "Awaiting Dispatch" stage
    And I load the current case

  @MPAMWorkflow @Smoketests @MPAMRegression
  Scenario: User enters a date of dispatch and closes the case
    And I enter a dispatched date
    When I select the "Dispatched, close case" action at Awaiting Dispatch stage
    Then the case should be closed

  @SmokeTests @MPAMRegression
  Scenario: User can see which response channel was selected at Draft stage
    Then I can see the previous selected response channel is still selected

  @MPAMWorkflow @SmokeTests @MPAMRegression
  Scenario: User selects that the case requires follow-up actions after being dispatched
    And I enter a dispatched date
    When I select the "Dispatched (follow-up)" action at Awaiting Dispatch stage
    And I enter a follow-up date
    And I enter follow-up details and confirm
    Then the follow-up due date should be visible in the "Drafting" workstack
    And the case should be moved to the "Dispatched (follow-up)" stage
    And the case should be allocated to me in the summary
    And the follow-up due date should be visible in the summary
    And a details of follow-up note should be visible showing the entered details

  @MPAMWorkflow @SmokeTests @MPAMRegression
  Scenario: User selects that the follow up is complete at Dispatched (follow-up) stage
    And I enter a dispatched date
    When I select the "Dispatched (follow-up)" action at Awaiting Dispatch stage
    And I enter a follow-up date
    And I enter follow-up details and confirm
    And I load the current case
    When I select the "Follow-up completed" action at Dispatched (follow-up) stage
    Then the case should be closed

  @MPAMWorkflow @SmokeTests @MPAMRegression
  Scenario: User selects to close the case without completing follow-up action
    And I enter a dispatched date
    When I select the "Dispatched (follow-up)" action at Awaiting Dispatch stage
    And I enter a follow-up date
    And I enter follow-up details and confirm
    And I load the current case
    When I select the "Close Follow-up actioned not completed" action at Dispatched (follow-up) stage
    And enter a reason for not completing the follow up action
    Then the case should be closed
    And a follow-up not completed note should be visible showing the entered reason

  @Validation
  Scenario Outline: User triggers error message to be displayed at Awaiting Dispatch stage
    And I trigger the "<errorType>" error message at Awaiting Dispatch stage by not entering the correct information
    Then then the "<errorType>" error message should be displayed at a Dispatch stage
    Examples:
      | errorType                               |
      | Actions Required                        |
      | Dispatched Date Required                |
      | Follow-up Date Required                 |
      | Follow-up Details Required              |
      | Follow-up Not Completed Reason Required |