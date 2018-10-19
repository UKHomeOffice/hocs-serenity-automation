Feature:  HOCS User is able to Dispatch a Response

  Background:
    Given I am user "<string>"
    And I am at the "Dispatch" stage
    
  @HOCS-542
  Scenario: User has a hard copy of a case to dispatch, they decide to reject it and fill in a rejection reason
    When I select the "reject"
    And I fill in a rejection reason
    Then the case is passed back to the private office stage
    And the "nominated person" for the "private office team" receive the "dispatch rejected email"
    And I am returned to my home screen
    
  @HOCS-542
  Scenario: User has a hard copy of a case to dispatch, they decide to reject it and don't fill in a rejection reason
    When I select the "reject"
    And I don't fill in a rejection reason
    Then I receive an error message
    
  @HOCS-443
  Scenario: User has a hard copy of a case to dispatch, they decide to accept the case
    When I "dispatch" the case
    Then the case is completed
    And I am taken to the "home" page
    
  @HOCS-443
  Scenario: User has a hard copy of a case to dispatch, they decide to accept the case, and the case needs to have a copy sent to Number 10
    When I "dispatch" the case
    And the case had the "send copy to number 10" box checked during the "data entry" stage
    Then the case goes to the "Send copy to number 10" stage
    And the "nominated person" in the "transfers and number 10" team get a notification email
    And I am taken to the "home" page
