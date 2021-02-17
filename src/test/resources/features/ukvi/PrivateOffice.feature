@PrivateOffice @UKVI
Feature: PrivateOffice

  Background:
    Given I log in to "DECS" as user "DECS_USER"
    And I create a "MPAM" case and move it to the "Private Office" stage
    And I load and claim the current case

  @Navigation
  Scenario: User selects the Dispatched action
    When I select the "Dispatched" action at Private Office stage
    Then the "MPAM Private Office" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User selects the Draft rejected by Private Office action
    When I select the "Draft rejected by Private Office" action at Private Office stage
    Then the "MPAM Rejected by Private Office" page should be displayed
    And the rejection reason entry box should be visible
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @UKVIRegression
  Scenario: User can see which response channel was selected at Draft stage
    Then I can see the previous selected response channel is still selected

  @UKVIWorkflow @UKVIRegression
  Scenario: User rejects the case back to drafting
    When I select the "Draft rejected by Private Office" action at Private Office stage
    And I submit a reason to reject the case back to Draft stage
    Then the case should be moved to the "Draft" stage
    And a rejection note should be visible showing the reason for rejection
    And I navigate to the "home" page
    And I view the MPAM case in the appropriate "Draft" stage workstack
    Then the stage that the case was rejected at should be displayed in the rejected workstack column

  @UKVIWorkflow @UKVIRegression
  Scenario: User enters a date of dispatch and closes the case
    When I select the "Dispatched" action at Private Office stage
    And I enter a date of dispatch and confirm to close the case
    Then the case should be closed

  @UKVIWorkflow @UKVIRegression
  Scenario: User selects that the case requires follow-up actions after being dispatched
    When I select the "Dispatched (follow-up)" action at Private Office stage
    And I enter a dispatched date
    And I enter a follow-up date
    And I enter follow-up details and confirm
    Then the follow-up due date should be visible in the "Private Office" workstack
    And the case should be moved to the "Dispatched (follow-up)" stage
    And the case "should" be allocated to me in the summary
    And the follow-up due date should be visible in the summary
    And a details of follow-up note should be visible showing the entered details

  @UKVIWorkflow @UKVIRegression
  Scenario: User selects that the follow up is complete at Dispatched (follow-up) stage
    When I select the "Dispatched (follow-up)" action at Private Office stage
    And I enter a dispatched date
    And I enter a follow-up date
    And I enter follow-up details and confirm
    And I load the current case
    When I select the "Follow-up completed" action at Dispatched (follow-up) stage
    Then the case should be closed

  @UKVIWorkflow @UKVIRegression
  Scenario: User selects to close the case without completing follow-up action
    When I select the "Dispatched (follow-up)" action at Private Office stage
    And I enter a dispatched date
    And I enter a follow-up date
    And I enter follow-up details and confirm
    And I load the current case
    When I select the "Close Follow-up actioned not completed" action at Dispatched (follow-up) stage
    And enter a reason for not completing the follow up action
    Then the case should be closed
    And a follow-up not completed note should be visible showing the entered reason

  @Validation
  Scenario Outline: User triggers error message to be displayed at Private Office
    When the user triggers the "<errorType>" error message at Private Office by not entering the correct information
    Then then the "<errorType>" error message should be displayed at a Dispatch stage
    Examples:
      | errorType                               |
      | Actions Required                        |
      | Dispatched Date Required                |
      | Follow-up Date Required                 |
      | Follow-up Details Required              |
      | Follow-up Not Completed Reason Required |

  @Campaigns
  Scenario: User moves a case into a Campaign from the Private Office stage
    When I move the case into a Campaign from the "Private Office" stage
    And I load the current case
    Then the case is added to the correct Campaign