Feature:  HOCS User is able to Dispatch a Response

  Background:
    Given I am user "EAMON"
    And I am at the "DISPATCH" stage

  @HOCS-542
  Scenario: User has a hard copy of a case to dispatch, they decide to reject it and fill in a rejection reason
    When I "reject" the case
    And I enter "<string>" in the "Reject Reason" field
    Then the case should be moved to the "Private Office" stage
    #And the "nominated person" for the "private office team" receive the "dispatch rejected email"
    And I am returned to my home screen

  @HOCS-542
  Scenario: User has a hard copy of a case to dispatch, they decide to reject it and don't fill in a rejection reason
    When I attempt to reject a case without reason
    Then an error message is displayed

  @HOCS-443
  Scenario: User has a hard copy of a case to dispatch, they decide to accept the case
    When I "dispatch" the case
    Then the case is completed
    And I am taken to the "home" page

  @HOCS-443
  Scenario: User has a hard copy of a case to dispatch, they decide to accept the case, and the case needs to have a copy sent to Number 10
    Given the case had the "send copy to number 10" box checked
    When I "dispatch" the case
    Then the case should be moved to the "Send copy to number 10" stage
    And the "nominated person" in the "transfers and number 10" team get a notification email
    And I am taken to the "home" page

  @Navigation
  Scenario: Clicking the Back to dashboard button on the allocate case screen at the Dispatch stage should take the user back to the dashboard
    And I click the back to dashboard button
    Then I should be taken to the homepage

  @Validation
  Scenario: User must select a radio button when asked if they able to dispatch the case at the Dispatch Stage
    And I click the continue button on the are you able to dispatch screen
    Then an error message should be displayed as I have selected whether the case can be dispatched

  @Validation
  Scenario: User must enter their reason for not being able to dispatch case in the text box at the Dispatch Stage
    And I click the finish button on the unable to dispatch screen
    Then an error message should be displayed as I have not entered a reason for not dispatching in the text box

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Dispatch stage
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not added any text into the case note text box