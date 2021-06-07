@AwaitingDispatch @UKVI
Feature: Awaiting Dispatch

  Background:
    Given I am logged into "DECS" as user "UKVI_USER"
    And I create a "MPAM" case and move it to the "Awaiting Dispatch" stage
    And I load the current case

  @UKVIWorkflow @FailingRegression
  Scenario: User enters a date of dispatch and closes the case
    And I enter a dispatched date
    When I select the "Dispatched, close case" action at Awaiting Dispatch stage
    Then the case should be closed

  @UKVIWorkflow @FailingRegression
  Scenario: User selects that the case requires follow-up actions after being dispatched
    And I enter a dispatched date
    When I select the "Dispatched (follow-up)" action at Awaiting Dispatch stage
    And I enter a follow-up date
    And I enter follow-up details and confirm
    Then the follow-up due date should be visible in the "Drafting" workstack
    And the case should be moved to the "Dispatched (follow-up)" stage
    And the case "should" be allocated to me in the summary
    And the follow-up due date should be visible in the summary
    And a details of follow-up note should be visible showing the entered details

  @UKVIWorkflow @FailingRegression
  Scenario: User selects that the follow up is complete at Dispatched (follow-up) stage
    And I enter a dispatched date
    When I select the "Dispatched (follow-up)" action at Awaiting Dispatch stage
    And I enter a follow-up date
    And I enter follow-up details and confirm
    And I load the current case
    When I select the "Follow-up completed" action at Dispatched (follow-up) stage
    Then the case should be closed

  @UKVIWorkflow @FailingRegression
  Scenario: User selects to close the case without completing follow-up action
    And I enter a dispatched date
    And I select the "Dispatched (follow-up)" action at Awaiting Dispatch stage
    And I enter a follow-up date
    And I enter follow-up details and confirm
    And I load the current case
    When I select the "Close Follow-up actioned not completed" action at Dispatched (follow-up) stage
    And enter a reason for not completing the follow up action
    Then the case should be closed
    And a follow-up not completed note should be visible showing the entered reason

  @UKVIWorkflow @FailingRegression
  Scenario: User selects to return the case to the Draft stage
    When I select the "Return to Draft" action at Awaiting Dispatch stage
    And I submit a reason to reject the case back to the Draft stage
    Then the case should be moved to the "Draft" stage
    And a rejection note should be visible showing the reason for rejection
    And I navigate to the "Dashboard" page
    And I view the MPAM case in the appropriate "Draft" stage workstack
    Then the stage that the case was rejected at should be displayed in the rejected workstack column

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

  @Campaigns
  Scenario: User moves a case into a Campaign from the Awaiting Dispatch stage
    When I move the case into a Campaign from the "Awaiting Dispatch" stage
    And I load the current case
    Then the case is added to the correct Campaign