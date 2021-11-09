@PrivateOffice @MPAM
Feature: PrivateOffice

  Background:
    Given I am logged into "CS" as user "MPAM_USER"

  @Navigation
  Scenario: User selects the Dispatched action
    And I create a "MPAM" case and move it to the "Private Office" stage
    And I load and claim the current case
    When I select a response channel
    And I select the "Dispatched" action at Private Office stage
    Then the "MPAM Private Office" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User selects the Draft rejected by Private Office action
    And I create a "MPAM" case and move it to the "Private Office" stage
    And I load and claim the current case
    When I select the "Draft rejected by Private Office" action at Private Office stage
    Then the "MPAM Rejected by Private Office" page should be displayed
    And the rejection reason entry box should be visible
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @MPAMWorkflow @MPAMRegression2
  Scenario: User rejects a UKVI business area MPAM case at Private Office
    And I create a MPAM case with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "Private Office" stage
    And I load and claim the current case
    When I select the "Draft rejected by private office" action at Private Office stage
    And I submit a reason to reject the case back to Draft stage
    Then the case should be moved to the "QA" stage
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case

  @MPAMWorkflow @MPAMRegression2
  Scenario: User rejects a EUSS business area MPAM case at Private Office
    And I create a MPAM case with "EUSS" as the Business Area and "Ministerial" as the Reference Type and move it to the "Private Office" stage
    And I load and claim the current case
    When I select the "Draft rejected by private office" action at Private Office stage
    And I submit a reason to reject the case back to Draft stage
    Then the case should be moved to the "Draft" stage
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And I navigate to the "Dashboard" page
    And I view the MPAM case in the appropriate "Draft" stage workstack
    Then the stage that the case was rejected at should be displayed in the rejected workstack column

  @MPAMWorkflow
  Scenario: As a Private Office user, I want to approve an eligible case for a Ministerial Dispatch, so that the reply can be dispatched
    And I create a MPAM case with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "Private Office" stage
    And I load and claim the current case
    When I select the "Approved (ministerial dispatch)" action at Private Office stage
    Then the case should be moved to the "Awaiting Dispatch (Ministerial)" stage
    And the summary should display the owning team as "Awaiting Dispatch: UKVI/BF/IE Ministerial"

  @MPAMWorkflow
  Scenario: As a Private Office user, I want to approve an eligible case for a Local Dispatch, so that the reply can be dispatched
    And I create a MPAM case with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "Private Office" stage
    And I load and claim the current case
    When I select the "Approved (local dispatch)" action at Private Office stage
    Then the case should be moved to the "Awaiting Dispatch (Local)" stage
    And the summary should display the owning team as "Awaiting Dispatch: UKVI/BF/IE Ministerial"

  @MPAMWorkflow @MPAMRegression2
  Scenario: As a Private Office user, I want to dispatch an elibigle case, so that the case can be closed
    And I create a MPAM case with "EUSS" as the Business Area and "Ministerial" as the Reference Type and move it to the "Private Office" stage
    And I load and claim the current case
    When I select a response channel
    And I select the "Dispatched" action at Private Office stage
    And I enter a date of dispatch and confirm to close the case
    Then the case should be closed

  @MPAMWorkflow @MPAMRegression2
  Scenario: As a Private Office user, I want to dispatch an eligible case with a follow-up action set, so that I record the need for the follow-up##
    And I create a MPAM case with "EUSS" as the Business Area and "Ministerial" as the Reference Type and move it to the "Private Office" stage
    And I load and claim the current case
    When I select a response channel
    And I select the "Dispatched (follow-up)" action at Private Office stage
    And I enter a dispatched date
    And I enter a follow-up date
    And I enter follow-up details and confirm
    Then the follow-up due date should be visible in the "Private Office" workstack
    And the case should be moved to the "Dispatched (follow-up)" stage
    And the case "should" be allocated to me in the summary
    And the follow-up due date should be visible in the summary
    And a Details of follow up note should be visible in the timeline showing the submitted details of the required action

  @MPAMWorkflow @MPAMRegression2
  Scenario: User selects that the follow up is complete at Dispatched (follow-up) stage
    And I create a MPAM case with "EUSS" as the Business Area and "Ministerial" as the Reference Type and move it to the "Private Office" stage
    And I load and claim the current case
    When I select a response channel
    And I select the "Dispatched (follow-up)" action at Private Office stage
    And I enter a dispatched date
    And I enter a follow-up date
    And I enter follow-up details and confirm
    And I load the current case
    When I select the "Follow-up completed" action at Dispatched (follow-up) stage
    Then the case should be closed

  @MPAMWorkflow @MPAMRegression2
  Scenario: User selects to close the case without completing follow-up action
    And I create a MPAM case with "EUSS" as the Business Area and "Ministerial" as the Reference Type and move it to the "Private Office" stage
    And I load and claim the current case
    When I select a response channel
    And I select the "Dispatched (follow-up)" action at Private Office stage
    And I enter a dispatched date
    And I enter a follow-up date
    And I enter follow-up details and confirm
    And I load the current case
    When I select the "Close Follow-up actioned not completed" action at Dispatched (follow-up) stage
    And enter a reason for not completing the follow up action
    Then the case should be closed
    And a Follow up not completed note should be visible in the timeline showing the submitted reason for not completing the action

  @Validation
  Scenario Outline: User tests validation at the Private Office stage
    And I create a MPAM case with "EUSS" as the Business Area and "Ministerial" as the Reference Type and move it to the "Private Office" stage
    And I load and claim the current case
    And I trigger the "<errorType>" error message at the "Private Office" stage
    Then the "<errorType>" error message is displayed at the "Private Office" stage
    Examples:
      | errorType                     |
      | RESPONSE CHANNEL REQUIRED     |
      | ACTIONS REQUIRED              |
      | CAMPAIGN REQUIRED             |
      | DISPATCHED DATE REQUIRED      |
      | FOLLOW-UP DUE DATE REQUIRED   |
      | DETAILS OF FOLLOW-UP REQUIRED |
      | REJECTION REASON REQUIRED     |

  @MPAMWorkflow @MPAMRegression2
  Scenario: User moves a case into a Campaign from the Private Office stage
    And I create a "MPAM" case and move it to the "Private Office" stage
    And I load and claim the current case
    When I move the case into a Campaign from the "Private Office" stage
    And I load the current case
    Then the case is added to the correct Campaign