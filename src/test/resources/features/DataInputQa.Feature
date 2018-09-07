Feature: HOCS User is able to QA Data Input

  Background:
    Given I am user "Dom"
    And I am at the "Data Input QA" stage

  Scenario: I must select a Data Input QA response
    When I do not select a Data Input QA response
    Then an error message is displayed

  @HOCS-441 @HOCS-438
  Scenario: User can accept Data Input at the QA Stage
    When I select a Data Input QA decision of "Accept"
    Then the case is moved to the "Markup" stage

  @HOCS-441 @HOCS-438
  Scenario: User can reject Data Input at the QA Stage
    When I select a Data Input QA decision of "Reject"
    Then the case is moved to the "Data Input" stage